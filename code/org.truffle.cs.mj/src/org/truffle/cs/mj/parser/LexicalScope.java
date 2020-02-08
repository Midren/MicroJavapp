package org.truffle.cs.mj.parser;

import org.truffle.cs.mj.parser.identifiertable.IdentifiersTable;
import org.truffle.cs.mj.parser.identifiertable.TypeTable;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class LexicalScope {
    private String name;
    private final LexicalScope parent;
    int depth;
    IdentifiersTable localIdentifiers;

    public LexicalScope(LexicalScope scope, String name) {
        this.name = name;
        this.parent = scope;
        this.localIdentifiers = new IdentifiersTable();
        if (scope != null)
            this.depth = scope.depth + 1;
        else
            this.depth = 0;
    }

    String getName() {
        return name;
    }

    LexicalScope getParentScope() {
        return parent;
    }

    public FrameDescriptor getFrameDescriptor() {
        return localIdentifiers.getFrameDescriptor();
    }

    public void addVariable(String identifier, TypeDescriptor typeDescriptor) {
        localIdentifiers.addVariable(identifier, typeDescriptor);
    }

    public FrameSlot getFrameSlot(String identifier) {
        return localIdentifiers.getFrameSlot(identifier);
    }

    public FrameSlot getVisibleFrameSlot(String identifier) {
        LexicalScope tmpLexicalScope = this;
        FrameSlot frameSlot = null;
        while (tmpLexicalScope != null) {
            frameSlot = tmpLexicalScope.getFrameSlot(identifier);
            if (frameSlot != null)
                break;
            tmpLexicalScope = tmpLexicalScope.getParentScope();
        }
        return frameSlot;
    }

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return TypeTable.getInstance().getTypeDescriptor(identifier);
    }

    public TypeDescriptor getVisibleIdentifierDescriptor(String identifier) {
        LexicalScope tmpLexicalScope = this;
        TypeDescriptor typeDescriptor = null;
        while (tmpLexicalScope != null) {
            typeDescriptor = tmpLexicalScope.getIdentifierDescriptor(identifier);
            if (typeDescriptor != null)
                break;
            tmpLexicalScope = tmpLexicalScope.getParentScope();
        }
        return typeDescriptor;
    }

    public TypeDescriptor getIdentifierDescriptor(String identifier) {
        return localIdentifiers.getIdentifierDescriptor(identifier);
    }
}
