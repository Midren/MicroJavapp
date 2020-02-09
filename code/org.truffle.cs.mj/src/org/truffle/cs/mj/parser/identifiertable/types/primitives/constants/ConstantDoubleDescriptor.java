package org.truffle.cs.mj.parser.identifiertable.types.primitives.constants;

import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;

public class ConstantDoubleDescriptor extends DoubleDescriptor implements ConstantTypeDescriptor {

    private static ConstantDoubleDescriptor instance = new ConstantDoubleDescriptor();

    public static ConstantDoubleDescriptor getInstance() {
        return instance;
    }

}
