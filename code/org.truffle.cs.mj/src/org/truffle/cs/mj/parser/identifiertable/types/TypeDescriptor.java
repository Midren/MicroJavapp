package org.truffle.cs.mj.parser.identifiertable.types;

import com.oracle.truffle.api.frame.FrameSlotKind;

public interface TypeDescriptor {

    FrameSlotKind getSlotKind();

    Object getDefaultValue();

}
