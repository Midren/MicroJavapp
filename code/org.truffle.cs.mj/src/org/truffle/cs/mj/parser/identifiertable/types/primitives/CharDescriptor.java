package org.truffle.cs.mj.parser.identifiertable.types.primitives;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameSlotKind;

public class CharDescriptor implements TypeDescriptor {
    private static CharDescriptor instance = new CharDescriptor();

    public CharDescriptor getInstance() {
        return instance;
    }

    public FrameSlotKind getSlotKind() {
        return FrameSlotKind.Byte;
    }

    public Object getDefaultValue() {
        return '\0';
    }
}
