package org.truffle.cs.mj.parser;

import org.truffle.cs.mj.parser.identifiertable.IdentifiersTable;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class LexicalScope {
    private String name;
    private final LexicalScope parent;
    private int depth;
    IdentifiersTable localIdentifiers;

    public LexicalScope(LexicalScope scope, String name) {
        this.name = name;
        this.parent = scope;
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

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return localIdentifiers.getTypeDescriptor(identifier);
    }
}
