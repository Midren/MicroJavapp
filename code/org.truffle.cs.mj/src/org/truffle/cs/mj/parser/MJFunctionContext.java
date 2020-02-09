package org.truffle.cs.mj.parser;

import java.util.HashMap;

import org.graalvm.collections.Pair;
import org.truffle.cs.mj.nodes.MJConstantNodeFactory;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJVariableNodeFactory;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.nodes.MJStatementNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJReadLocalVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJWriteLocalVariableNode;
import org.truffle.cs.mj.nodes.MJReadParameterNode;
import org.truffle.cs.mj.nodes.MJConstantNodeFactory;
import org.truffle.cs.mj.parser.identifiertable.TypeTable;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantTypeDescriptor;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class MJFunctionContext {
    private LexicalScope currentLexicalScope;
    private HashMap<String, Pair<Integer, TypeDescriptor>> parameterNames;
    private HashMap<String, Pair<Object, TypeDescriptor>> globalConstVars;

    public MJFunctionContext() {
        this.currentLexicalScope = new LexicalScope(null, "global");
        parameterNames = new HashMap<>();
        globalConstVars = new HashMap<>();
    }

    public void stepInBlock() {
        LexicalScope innerBlockScope = new LexicalScope(currentLexicalScope, "innerBlock_" + currentLexicalScope.depth);
        currentLexicalScope = innerBlockScope;
    }

    public void stepOutBlock() {
        currentLexicalScope = currentLexicalScope.getParentScope();
    }

    public void createGlobalConstant(String typeName, String varname, Object value) {
        if (isDeclared(varname))
            throw new Error("Double declaration");
        TypeDescriptor typeDescriptor = currentLexicalScope.getTypeDescriptor(typeName);
        if (value instanceof Integer && !(typeDescriptor instanceof IntDescriptor))
            throw new Error("Illegal implicit cast");
        if (value instanceof Character && !(typeDescriptor instanceof CharDescriptor))
            throw new Error("Illegal implicit cast");
        globalConstVars.put(varname, Pair.create(value, typeDescriptor));
    }

    public void createParameter(String typeName, String varname) {
        if (isDeclared(varname))
            throw new Error("Double declaration");
        parameterNames.put(varname, Pair.create(parameterNames.size(),
                        currentLexicalScope.getTypeDescriptor(typeName)));
    }

    public void createLocalVar(String typeName, String varname) {
        createLocalVar(typeName, varname, false);
    }

    public void createLocalVar(String typeName, String varname, boolean isConstant) {
        if (isDeclared(varname))
            throw new Error("Double declaration");
        TypeDescriptor typeDescriptor = currentLexicalScope.getTypeDescriptor(typeName, isConstant);
        if (typeDescriptor == null)
            throw new Error("Type " + typeName + " was not defined");
        currentLexicalScope.addVariable(varname, typeDescriptor);
    }

    public MJStatementNode createConstLocalVarAndWrite(String typeName, String varname, MJExpressionNode value) {
        createLocalVar(typeName, varname, true);
        return MJVariableNodeFactory.MJWriteConstantLocalVariableNodeGen.create(value,
                        currentLexicalScope.getVisibleFrameSlot(varname),
                        currentLexicalScope.getVisibleIdentifierDescriptor(varname));
    }

    public MJExpressionNode readVariable(String varname) {
        if (globalConstVars.containsKey(varname))
            return readGlobalConstant(varname);
        if (parameterNames.containsKey(varname))
            return readParameter(varname);
        return readLocalVar(varname);
    }

    public MJStatementNode writeVariable(String varname, MJExpressionNode value) {
        if (globalConstVars.containsKey(varname))
            throw new Error("Cannot write to constant");
        if (parameterNames.containsKey(varname))
            throw new Error("Cannot write to parameter");
        return writeLocalVar(varname, value);
    }

    public FrameDescriptor getContextFrameDescriptor() {
        return currentLexicalScope.getFrameDescriptor();
    }

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return currentLexicalScope.getTypeDescriptor(identifier);
    }

    private boolean isDeclared(String varname) {
        if (globalConstVars.containsKey(varname))
            return true;
        if (parameterNames.containsKey(varname))
            return true;
        if (currentLexicalScope.getVisibleFrameSlot(varname) != null)
            return true;
        return false;
    }

    private MJExpressionNode readLocalVar(String varname) {
        FrameSlot frameSlot = currentLexicalScope.getVisibleFrameSlot(varname);
        if (frameSlot == null) {
            throw new Error("Variable was not declared");
        }
        return MJVariableNodeFactory.MJReadLocalVariableNodeGen.create(frameSlot,
                        currentLexicalScope.getVisibleIdentifierDescriptor(varname));
    }

    private MJExpressionNode readParameter(String varname) {
        if (!parameterNames.containsKey(varname))
            throw new Error("Variable was not declared");
        Pair<Integer, TypeDescriptor> p = parameterNames.get(varname);
        return new MJReadParameterNode(p.getLeft().intValue(), p.getRight().getInstance());
    }

    private MJExpressionNode readGlobalConstant(String varname) {
        if (!globalConstVars.containsKey(varname))
            throw new Error("Variable was not declared");
        Pair<Object, TypeDescriptor> p = globalConstVars.get(varname);
        if (p.getRight() instanceof IntDescriptor)
            return MJConstantNodeFactory.IntNodeGen.create((int) p.getLeft());
        else
            return MJConstantNodeFactory.CharNodeGen.create((char) p.getLeft());
    }

    private MJStatementNode writeLocalVar(String varname, MJExpressionNode value) {
        FrameSlot frameSlot = currentLexicalScope.getVisibleFrameSlot(varname);
        if (frameSlot == null) {
            throw new Error("Variable was not declared");
        }
        TypeDescriptor typeDescriptor = currentLexicalScope.getVisibleIdentifierDescriptor(varname);
        if (typeDescriptor instanceof ConstantTypeDescriptor)
            throw new Error("Cannot write to constant");
        return MJVariableNodeFactory.MJWriteLocalVariableNodeGen.create(value, frameSlot, typeDescriptor);
    }

}
