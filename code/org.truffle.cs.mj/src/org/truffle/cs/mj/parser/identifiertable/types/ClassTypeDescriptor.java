package org.truffle.cs.mj.parser.identifiertable.types;

import com.oracle.truffle.api.frame.FrameSlotKind;

public class ClassTypeDescriptor implements TypeDescriptor {

    private final TypeDescriptor typeDescriptor;

    public ClassTypeDescriptor(TypeDescriptor typeDescriptor) {
        this.typeDescriptor = typeDescriptor;
    }

    public FrameSlotKind getSlotKind() {
        return null;
    }

    public Object getDefaultValue() {
        return null;
    }

    public TypeDescriptor getInstance() {
        return null;
    }

}
