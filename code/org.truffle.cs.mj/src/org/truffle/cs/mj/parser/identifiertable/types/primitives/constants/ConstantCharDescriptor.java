package org.truffle.cs.mj.parser.identifiertable.types.primitives.constants;

import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;

public class ConstantCharDescriptor extends CharDescriptor implements ConstantTypeDescriptor {
    private static ConstantCharDescriptor instance = new ConstantCharDescriptor();

    public static ConstantCharDescriptor getInstance() {
        return instance;
    }

}
