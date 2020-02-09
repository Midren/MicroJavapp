package org.truffle.cs.mj.parser.identifiertable.types.primitives;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameSlotKind;

public class BoolDescriptor implements TypeDescriptor {

    private static BoolDescriptor instance = new BoolDescriptor();

    public BoolDescriptor getInstance() {
        return instance;
    }

    public FrameSlotKind getSlotKind() {
        return FrameSlotKind.Boolean;
    }

    public Object getDefaultValue() {
        return false;
    }
}
