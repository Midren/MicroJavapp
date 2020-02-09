package org.truffle.cs.mj.parser.identifiertable.types;

import com.oracle.truffle.api.frame.FrameSlotKind;

public interface TypeDescriptor {

    public abstract TypeDescriptor getInstance();

    FrameSlotKind getSlotKind();

    Object getDefaultValue();

}
