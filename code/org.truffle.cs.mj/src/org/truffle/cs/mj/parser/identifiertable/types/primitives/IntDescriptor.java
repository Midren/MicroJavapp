package org.truffle.cs.mj.parser.identifiertable.types.primitives;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameSlotKind;

public class IntDescriptor implements TypeDescriptor {
    private static IntDescriptor instance = new IntDescriptor();

    public static IntDescriptor getInstance() {
        return instance;
    }

    public FrameSlotKind getSlotKind() {
        return FrameSlotKind.Int;
    }

    public Object getDefaultValue() {
        return 0;
    }
}
