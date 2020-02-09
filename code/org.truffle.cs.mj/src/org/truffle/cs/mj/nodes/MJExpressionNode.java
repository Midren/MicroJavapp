package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantTypeDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

@TypeSystemReference(MJTypes.class)
public abstract class MJExpressionNode extends Node {

    public abstract TypeDescriptor getType();

    public abstract Object executeGeneric(VirtualFrame frame);

    public char executeChar(VirtualFrame frame) throws UnexpectedResultException {
        return MJTypesGen.expectCharacter(executeGeneric(frame));
    }

    public int executeI32(VirtualFrame frame) throws UnexpectedResultException {
        return MJTypesGen.expectInteger(executeGeneric(frame));
    }

    public double executeDouble(VirtualFrame frame) throws UnexpectedResultException {
        return MJTypesGen.expectDouble(executeGeneric(frame));
    }

    public boolean executeBool(VirtualFrame frame) throws UnexpectedResultException {
        return MJTypesGen.expectBoolean(executeGeneric(frame));
    }

    public static boolean isBoolVariable(TypeDescriptor typeDescriptor) {
        return typeDescriptor instanceof BoolDescriptor;
    }

    public static boolean isCharVariable(TypeDescriptor typeDescriptor) {
        return typeDescriptor instanceof CharDescriptor;
    }

    public static boolean isIntVariable(TypeDescriptor typeDescriptor) {
        return typeDescriptor instanceof IntDescriptor;
    }

    public static boolean isDoubleVariable(TypeDescriptor typeDescriptor) {
        return typeDescriptor instanceof DoubleDescriptor;
    }

    public static boolean isConstant(TypeDescriptor typeDescriptor) {
        return typeDescriptor instanceof ConstantTypeDescriptor;
    }

}
