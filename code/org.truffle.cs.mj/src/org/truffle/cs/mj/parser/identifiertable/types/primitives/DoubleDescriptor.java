package org.truffle.cs.mj.parser.identifiertable.types.primitives;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameSlotKind;

public class DoubleDescriptor implements TypeDescriptor {

    private static DoubleDescriptor instance = new DoubleDescriptor();

    public static DoubleDescriptor getInstance() {
        return instance;
    }

    public FrameSlotKind getSlotKind() {
        return FrameSlotKind.Double;
    }

    public Object getDefaultValue() {
        return 0.0;
    }
}
