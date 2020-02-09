package org.truffle.cs.mj.parser.identifiertable.types.primitives.constants;

import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;

public class ConstantBoolDescriptor extends BoolDescriptor implements ConstantTypeDescriptor {
    private static ConstantBoolDescriptor instance = new ConstantBoolDescriptor();

    @Override
    public ConstantBoolDescriptor getInstance() {
        return instance;
    }
}
