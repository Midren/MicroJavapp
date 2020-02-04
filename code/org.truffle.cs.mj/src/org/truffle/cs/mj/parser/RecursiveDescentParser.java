package org.truffle.cs.mj.parser;

import static org.truffle.cs.mj.parser.Token.Kind.and;
import static org.truffle.cs.mj.parser.Token.Kind.assign;
import static org.truffle.cs.mj.parser.Token.Kind.break_;
import static org.truffle.cs.mj.parser.Token.Kind.charConst;
import static org.truffle.cs.mj.parser.Token.Kind.class_;
import static org.truffle.cs.mj.parser.Token.Kind.comma;
import static org.truffle.cs.mj.parser.Token.Kind.continue_;
import static org.truffle.cs.mj.parser.Token.Kind.else_;
import static org.truffle.cs.mj.parser.Token.Kind.eof;
import static org.truffle.cs.mj.parser.Token.Kind.final_;
import static org.truffle.cs.mj.parser.Token.Kind.ident;
import static org.truffle.cs.mj.parser.Token.Kind.if_;
import static org.truffle.cs.mj.parser.Token.Kind.lbrace;
import static org.truffle.cs.mj.parser.Token.Kind.lbrack;
import static org.truffle.cs.mj.parser.Token.Kind.lpar;
import static org.truffle.cs.mj.parser.Token.Kind.minus;
import static org.truffle.cs.mj.parser.Token.Kind.new_;
import static org.truffle.cs.mj.parser.Token.Kind.number;
import static org.truffle.cs.mj.parser.Token.Kind.or;
import static org.truffle.cs.mj.parser.Token.Kind.period;
import static org.truffle.cs.mj.parser.Token.Kind.plus;
import static org.truffle.cs.mj.parser.Token.Kind.print;
import static org.truffle.cs.mj.parser.Token.Kind.program;
import static org.truffle.cs.mj.parser.Token.Kind.rbrace;
import static org.truffle.cs.mj.parser.Token.Kind.rbrack;
import static org.truffle.cs.mj.parser.Token.Kind.read;
import static org.truffle.cs.mj.parser.Token.Kind.rem;
import static org.truffle.cs.mj.parser.Token.Kind.return_;
import static org.truffle.cs.mj.parser.Token.Kind.rpar;
import static org.truffle.cs.mj.parser.Token.Kind.semicolon;
import static org.truffle.cs.mj.parser.Token.Kind.slash;
import static org.truffle.cs.mj.parser.Token.Kind.times;
import static org.truffle.cs.mj.parser.Token.Kind.void_;
import static org.truffle.cs.mj.parser.Token.Kind.while_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.truffle.cs.mj.nodes.MJBinaryNode;
import org.truffle.cs.mj.nodes.MJBlock;
import org.truffle.cs.mj.nodes.MJConditionalNode;
import org.truffle.cs.mj.nodes.MJConstantIntNode;
import org.truffle.cs.mj.nodes.MJConstantIntNodeGen;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJExpressionStatement;
import org.truffle.cs.mj.nodes.MJFunction;
import org.truffle.cs.mj.nodes.MJInvokeNode;
import org.truffle.cs.mj.nodes.MJPrintNodeGen;
import org.truffle.cs.mj.nodes.MJReadParameterNode;
import org.truffle.cs.mj.nodes.MJReturnNode;
import org.truffle.cs.mj.nodes.MJStatementNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.AddNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJReadLocalVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJWriteLocalVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNodeFactory;
import org.truffle.cs.mj.nodes.MJWhileLoop;
import org.truffle.cs.mj.nodes.MJBinaryNodeFactory;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public final class RecursiveDescentParser {
    /** Maximum number of global variables per program */
    protected static final int MAX_GLOBALS = 32767;

    /** Maximum number of fields per class */
    protected static final int MAX_FIELDS = 32767;

    /** Maximum number of local variables per method */
    protected static final int MAX_LOCALS = 127;

    /** Last recognized token; */
    protected Token t;

    /** Lookahead token (not recognized).) */
    protected Token la;

    /** Shortcut to kind attribute of lookahead token (la). */
    protected Token.Kind sym;

    /** According scanner */
    public final RecursiveDescendScanner scanner;

    public RecursiveDescentParser(RecursiveDescendScanner scanner) {
        this.scanner = scanner;
        // Avoid crash when 1st symbol has scanner error.
        la = new Token(Token.Kind.none, 1, 1);
        firstExpr = EnumSet.of(ident, number, charConst, minus, lpar, new_);
        firstStat = EnumSet.of(ident, semicolon, lbrace, break_, continue_, if_,
                        print, read, return_, while_);
        firstMethodDecl = EnumSet.of(void_, ident);
    }

    public static enum CompOp {
        eq,
        ne,
        lt,
        le,
        gt,
        ge;

        public static CompOp invert(CompOp op) {
            switch (op) {
                case eq:
                    return ne;
                case ne:
                    return eq;
                case lt:
                    return ge;
                case le:
                    return gt;
                case gt:
                    return le;
                case ge:
                    return lt;
            }
            throw new IllegalArgumentException("Unexpected compare operator");
        }
    }

    private static enum Operands {
        B(1), // byte ( 8 bit signed)
        S(2), // short (16 bit signed)
        W(4); // word (32 bit signed)

        /** Size in bytes (8 bit) */
        public final int size;

        Operands(int size) {
            this.size = size;
        }
    }

    private static final Operands[] B = new Operands[]{Operands.B};
    private static final Operands[] S = new Operands[]{Operands.S};
    private static final Operands[] W = new Operands[]{Operands.W};
    private static final Operands[] BB = new Operands[]{Operands.B,
                    Operands.B};

    public static enum OpCode {
        load(B), //
        load_0, //
        load_1, //
        load_2, //
        load_3, //
        store(B), //
        store_0, //
        store_1, //
        store_2, //
        store_3, //
        getstatic(S), //
        putstatic(S), //
        getfield(S), //
        putfield(S), //
        const_0, //
        const_1, //
        const_2, //
        const_3, //
        const_4, //
        const_5, //
        const_m1, //
        const_(W), //
        add, //
        sub, //
        mul, //
        div, //
        rem, //
        neg, //
        shl, //
        shr, //
        inc(BB), //
        new_(S), //
        newarray(B), //
        aload, //
        astore, //
        baload, //
        bastore, //
        arraylength, //
        pop, //
        dup, //
        dup2, //
        jmp(S), //
        jeq(S), //
        jne(S), //
        jlt(S), //
        jle(S), //
        jgt(S), //
        jge(S), //
        call(S), //
        return_, //
        enter(BB), //
        exit, //
        read, //
        print, //
        bread, //
        bprint, //
        trap(B), //
        nop;

        private final Operands[] ops;

        private OpCode(Operands... operands) {
            this.ops = operands;
        }

        protected Collection<Operands> getOps() {
            return Arrays.asList(ops);
        }

        public int numOps() {
            return ops.length;
        }

        public int getOpsSize() {
            int size = 0;
            for (Operands op : ops) {
                size += op.size;
            }
            return size;
        }

        public int code() {
            return ordinal() + 1;
        }

        public String cleanName() {
            String name = name();
            if (name.endsWith("_")) {
                name = name.substring(0, name.length() - 1);
            }
            return name;
        }

        public static OpCode get(int code) {
            if (code < 1 || code > values().length) {
                return null;
            }
            return values()[code - 1];
        }
    }

    // TODO Exercise 3 - 6: implementation of parser

    /** Sets of starting tokens for some productions. */
    private EnumSet<Token.Kind> firstExpr, firstStat, firstMethodDecl;

    /** Reads ahead one symbol. */
    private void scan() {
        t = la;
        la = scanner.next();
        sym = la.kind;
    }

    /** Verifies symbol and reads ahead. */
    private void check(Token.Kind expected) {
        if (sym == expected) {
            scan();
        } else {
            throw new Error("Token " + expected + " excpeted");
        }
    }

    /**
     * Program = <br>
     * "program" ident <br>
     * { ConstDecl | VarDecl | ClassDecl } <br>
     * "{" { MethodDecl } "}" .
     */
    private void Program() {
        check(program);
        check(ident);
        for (;;) {
            if (sym == final_) {
                ConstDecl();
            } else if (sym == ident) {
                VarDecl();
            } else if (sym == class_) {
                ClassDecl();
            } else {
                break;
            }
        }
        check(lbrace);
        for (;;) {
            if (sym == rbrace || sym == eof) {
                break;
            } else if (firstMethodDecl.contains(sym)) {
                MethodDecl();
            }
        }
        check(rbrace);
    }

    /** ConstDecl = "final" Type ident "=" ( number | charConst ) ";" . */
    private void ConstDecl() {
        check(final_);
        Type();
        check(ident);
        check(assign);
        if (sym == number) {
            scan();
        } else if (sym == charConst) {
            scan();
        } else {
            throw new Error("Constant declaration");
        }
        check(semicolon);
    }

    /** VarDecl = Type ident { "," ident } ";" . */
    private void VarDecl() {
        Type();
        check(ident);
        while (sym == comma) {
            scan();
            check(ident);
        }
        check(semicolon);
    }

    /** ClassDecl = "class" ident "{" { VarDecl } "}" . */
    private void ClassDecl() {
        check(class_);
        check(ident);
        check(lbrace);
        while (sym == ident) {
            VarDecl();
        }
        check(rbrace);
    }

    /**
     * MethodDecl = <br>
     * ( Type | "void" ) ident "(" [ FormPars ] ")" <br>
     * ( ";" | { VarDecl } Block ) .
     */
    FrameDescriptor currentFrameDescriptor;

    public Map<String, FrameSlot> slots = new HashMap<>();

    public MJStatementNode createLocalVarWrite(String name, MJExpressionNode value) {
        FrameSlot frameSlot = slots.get(name);
        if (frameSlot == null) {
            frameSlot = currentFrameDescriptor.addFrameSlot(name);
            slots.put(name, frameSlot);
        }
        return MJVariableNodeFactory.MJWriteLocalVariableNodeGen.create(value, frameSlot);
    }

    public List<MJFunction> functions = new ArrayList<>();

    public HashMap<MJFunction, CallTarget> callAble = new HashMap<MJFunction, CallTarget>();

    public MJFunction getFunction(String Name) {
        for (MJFunction f : functions) {
            if (f.getName().equals(Name))
                return f;
        }
        return null;
    }

    public MJFunction getMain() {
        for (MJFunction f : functions) {
            if (f.getName().equals("main"))
                return f;
        }
        return null;
    }

    private ArrayList<String> parameterNames;

    MJFunction currentFun = null;

    private MJFunction MethodDecl() {
        currentFrameDescriptor = new FrameDescriptor();
        if (sym == ident) {
            Type();
        } else if (sym == void_) {
            scan();
        } else {
            throw new Error("Method declaration");
        }
        check(ident);
        String name = t.str;
        check(lpar);
        if (sym == ident) {
            parameterNames = FormPars();
        }
        check(rpar);
        while (sym == ident) {
            VarDecl();
        }
        currentFun = new MJFunction(name, Block(), currentFrameDescriptor);
        functions.add(currentFun);
        parameterNames = null;
        return currentFun;
    }

    /** FormPars = Type ident { "," Type ident } . */
    private ArrayList<String> FormPars() {
        ArrayList<String> parNames = new ArrayList<String>();
        Type();
        check(ident);
        parNames.add(t.str);
        while (sym == comma) {
            scan();
            Type();
            check(ident);
            parNames.add(t.str);
        }
        return parNames;
    }

    /** Type = ident . */
    private void Type() {
        check(ident);
        if (sym == lbrack) {
            scan();
            check(rbrack);
        }
    }

    /** Block = "{" { Statement } "}" . */
    private MJStatementNode Block() {
        check(lbrace);
        List<MJStatementNode> statements = Statements();
        check(rbrace);
        return new MJBlock(statements.toArray(new MJStatementNode[statements.size()]));
    }

    private List<MJStatementNode> Statements() {
        List<MJStatementNode> statementNodes = new ArrayList<>();
        for (;;) {
            if (firstStat.contains(sym)) {
                statementNodes.add(Statement());
            } else {
                break;
            }
        }
        return statementNodes;
    }

    /**
     * Statement = <br>
     * Designator ( Assignop Expr | ActPars | "++" | "--" ) ";" <br>
     * | "if" "(" Condition ")" Statement [ "else" Statement ] <br>
     * | "while" "(" Condition ")" Statement <br>
     * | "break" ";" <br>
     * | "return" [ Expr ] ";" <br>
     * | "read" "(" Designator ")" ";" <br>
     * | "print" "(" Expr [ comma number ] ")" ";" <br>
     * | Block <br>
     * | ";" .
     */
    private MJStatementNode Statement() {
        MJStatementNode curStatementNode = null;
        switch (sym) {
            // ----- assignment, method call, in- or decrement
            // ----- Designator ( Assignop Expr | ActPars | "++" | "--" ) ";"
            case ident:
                String des = Designator();
                switch (sym) {
                    case assign:
                        Assignop();
                        curStatementNode = createLocalVarWrite(des, Expr());
                        break;
                    case plusas:
                    case minusas:
                    case timesas:
                    case slashas:
                    case remas:
                        throw new Error("Unimplemented");
                    // break;
                    case lpar:
                        List<MJExpressionNode> params = ActPars();
                        // TODO: Check if function exists
                        MJFunction caleeFunction = getFunction(des);
                        CallTarget callTarget = callAble.get(caleeFunction);
                        if (callTarget == null) {
                            callTarget = Truffle.getRuntime().createCallTarget(caleeFunction);
                            callAble.put(caleeFunction, callTarget);
                        }
                        MJExpressionNode invoke = new MJInvokeNode(callTarget, params.toArray(new MJExpressionNode[params.size()]));
                        curStatementNode = new MJExpressionStatement(invoke);
                        break;
                    case pplus:
                        scan();
                        break;
                    case mminus:
                        scan();
                        break;
                    default:
                        throw new Error("Designator Follow");
                }
                check(semicolon);
                break;
            // ----- "if" "(" Condition ")" Statement [ "else" Statement ]
            case if_:
                scan();
                check(lpar);
                MJExpressionNode condition = Condition();
                check(rpar);
                MJStatementNode trueBlock = Statement();
                MJStatementNode falseBlock = null;
                if (sym == else_) {
                    scan();
                    falseBlock = Statement();
                }
                curStatementNode = new MJConditionalNode(condition, trueBlock, falseBlock);
                break;
            // ----- "while" "(" Condition ")" Statement
            case while_:
                scan();
                check(lpar);
                MJExpressionNode conditionNode = Condition();
                check(rpar);
                MJStatementNode block = Statement();
                curStatementNode = new MJWhileLoop(conditionNode, block);
                break;
            // ----- "break" ";"
            case break_:
                scan();
                check(semicolon);
                break;

            // ----- "break" ";"
            case continue_:
                scan();
                check(semicolon);
                break;
            // ----- "return" [ Expr ] ";"
            case return_:
                scan();
                MJExpressionNode retValue = null;
                if (sym != semicolon) {
                    retValue = Expr();
                } else {
                    break;
                }
                check(semicolon);
                curStatementNode = new MJReturnNode(retValue);
                break;
            // ----- "read" "(" Designator ")" ";"
            case read:
                scan();
                check(lpar);
                Designator();
                check(rpar);
                check(semicolon);
                break;
            // ----- "print" "(" Expr [ comma number ] ")" ";"
            case print:
                scan();
                check(lpar);
                MJExpressionNode expr = Expr();
                if (sym == comma) {
                    scan();
                    check(number);
                }
                check(rpar);
                check(semicolon);
                curStatementNode = MJPrintNodeGen.create(expr);
                break;
            case lbrace:
                curStatementNode = Block();
                break;
            case semicolon:
                scan();
                break;
            default:
                throw new Error("Invalid start...");
        }
        return curStatementNode;
    }

    /** ActPars = "(" [ Expr { "," Expr } ] ")" . */
    private List<MJExpressionNode> ActPars() {
        List<MJExpressionNode> exprsExpressionNodes = new ArrayList<>();
        check(lpar);
        if (firstExpr.contains(sym)) {
            for (;;) {
                exprsExpressionNodes.add(Expr());
                if (sym == comma) {
                    scan();
                } else {
                    break;
                }
            }
        }
        check(rpar);
        return exprsExpressionNodes;
    }

    /** Condition = CondTerm { "||" CondTerm } . */
    private MJExpressionNode Condition() {
// MJExpressionNode expressionNode = null;
        MJExpressionNode expressionNode = CondTerm();
        while (sym == or) {
            scan();
            expressionNode = MJBinaryNodeFactory.OrNodeGen.create(expressionNode, CondTerm());
        }
        return expressionNode;
    }

    /** CondTerm = CondFact { "&&" CondFact } . */
    private MJExpressionNode CondTerm() {
        MJExpressionNode expressionNode = CondFact();
        while (sym == and) {
            scan();
            expressionNode = MJBinaryNodeFactory.AndNodeGen.create(expressionNode, CondFact());
// CondFact();
        }
        return expressionNode;

    }

    /** CondFact = Expr Relop Expr . */
    private MJExpressionNode CondFact() {
        MJExpressionNode expressionNode = Expr();
        CompOp comp = Relop();
        switch (comp) {
            case ne:
                expressionNode = MJBinaryNodeFactory.NotEqualNodeGen.create(expressionNode, Expr());
                break;
            case lt:
                expressionNode = MJBinaryNodeFactory.LessNodeGen.create(expressionNode, Expr());
                break;
            case le:
                expressionNode = MJBinaryNodeFactory.LessEqualNodeGen.create(expressionNode, Expr());
                break;
            case eq:
                expressionNode = MJBinaryNodeFactory.EqualNodeGen.create(expressionNode, Expr());
                break;
            case ge:
                expressionNode = MJBinaryNodeFactory.GreaterEqualNodeGen.create(expressionNode, Expr());
                break;
            case gt:
                expressionNode = MJBinaryNodeFactory.GreaterNodeGen.create(expressionNode, Expr());
                break;
            default:
                break;
        }
        return expressionNode;
    }

    /** Expr = [ "-" ] Term { Addop Term } . */
    private MJExpressionNode Expr() {
        MJExpressionNode expressionNode = null;
        boolean neg = false;
        if (sym == minus) {
            scan();
            neg = true;
        }
        expressionNode = Term();
        while (sym == plus || sym == minus) {
            if (sym == plus) {
                Addop();
                expressionNode = MJBinaryNodeFactory.AddNodeGen.create(expressionNode, Term());
            } else if (sym == minus) {
                Addop();
                expressionNode = MJBinaryNodeFactory.SubtractNodeGen.create(expressionNode, Term());
            }
        }
        return expressionNode;
    }

    /** Term = Factor { Mulop Factor } . */
    private MJExpressionNode Term() {
        MJExpressionNode expressionNode = null;
        expressionNode = Factor();
        while (sym == times || sym == slash || sym == rem) {
            switch (Mulop()) {
                case mul:
                    expressionNode = MJBinaryNodeFactory.MultiplicationNodeGen.create(expressionNode, Factor());
                    break;
                case div:
                    expressionNode = MJBinaryNodeFactory.DividerNodeGen.create(expressionNode, Factor());
                    break;
                case rem:
                    expressionNode = MJBinaryNodeFactory.ModulationNodeGen.create(expressionNode, Factor());
                    break;
                default:
                    break;
            }
        }
        return expressionNode;
    }

    /**
     * Factor = <br>
     * Designator [ ActPars ] <br>
     * | number <br>
     * | charConst <br>
     * | "new" ident [ "[" Expr "]" ] <br>
     * | "(" Expr ")" .
     */
    private MJExpressionNode Factor() {
        MJExpressionNode expressionNode = null;
        switch (sym) {
            case abs:
                scan();
                check(lpar);
                Expr();
                check(rpar);
                break;
            case ident:
                String varname = Designator();
                if (sym == lpar) {
                    ActPars();
                } else {
                    // normal variable node

                    int index = parameterNames != null ? parameterNames.indexOf(varname) : 0;
                    if (index >= 0) {
                        expressionNode = new MJReadParameterNode(index);
                    } else {
                        // TODO: add checking
                        expressionNode = MJVariableNodeFactory.MJReadLocalVariableNodeGen.create(slots.get(varname));
                    }

                }
                break;
            case number:
                scan();
                expressionNode = MJConstantIntNodeGen.create(t.val);
                break;
            case charConst:
                scan();
                break;
            case new_:
                scan();
                check(ident);
                if (sym == lbrack) {
                    scan();
                    Expr();
                    check(rbrack);
                }
                break;
            case lpar:
                scan();
                Expr();
                check(rpar);
                break;
            default:
                throw new Error("Invalid fact");
        }
        return expressionNode;
    }

    /** Designator = ident { "." ident | "[" Expr "]" } . */
    private String Designator() {
        check(ident);
        while (sym == period || sym == lbrack) {
            if (sym == period) {
                scan();
                check(ident);
                throw new Error("Fields ignored for now");
            } else {
                // scan();
                // Expr();
                // check(rbrack);
                throw new Error("Arrays ignored for now...");
            }
        }
        return t.str;
    }

    /** Assignop = "=" | "+=" | "-=" | "*=" | "/=" | "%=" . */
    private OpCode Assignop() {
        OpCode op = OpCode.store;
        switch (sym) {
            case assign:
                op = OpCode.store;
                scan();
                break;
            case plusas:
                op = OpCode.add;
                scan();
                break;
            case minusas:
                op = OpCode.sub;
                scan();
                break;
            case timesas:
                op = OpCode.mul;
                scan();
                break;
            case slashas:
                op = OpCode.div;
                scan();
                break;
            case remas:
                op = OpCode.rem;
                scan();
                break;
            default:
                throw new Error("invalid assign operation");
        }
        return op;
    }

    /** Relop = "==" | "!=" | ">" | ">=" | "<" | "<=" . */
    private CompOp Relop() {
        CompOp op = CompOp.eq;
        switch (sym) {
            case eql:
                op = CompOp.eq;
                scan();
                break;
            case neq:
                op = CompOp.ne;
                scan();
                break;
            case lss:
                op = CompOp.lt;
                scan();
                break;
            case leq:
                op = CompOp.le;
                scan();
                break;
            case gtr:
                op = CompOp.gt;
                scan();
                break;
            case geq:
                op = CompOp.ge;
                scan();
                break;
            default:
                throw new Error("invalid rel operation " + sym);
        }
        return op;
    }

    /** Addop = "+" | "-" . */
    private OpCode Addop() {
        OpCode op = OpCode.add;
        switch (sym) {
            case plus:
                op = OpCode.add;
                scan();
                break;
            case minus:
                op = OpCode.sub;
                scan();
                break;
            default:
                throw new Error("invalid add operation");
        }
        return op;
    }

    /** Mulop = "*" | "/" | "%" . */
    private OpCode Mulop() {
        OpCode op = OpCode.mul;
        switch (sym) {
            case times:
                op = OpCode.mul;
                scan();
                break;
            case slash:
                op = OpCode.div;
                scan();
                break;
            case rem:
                op = OpCode.rem;
                scan();
                break;
            default:
                throw new Error("invalid mul operation");
        }
        return op;
    }

    public void parse() {
        scan(); // scan first symbol
        Program(); // start analysis

        check(eof);
    }
}