package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

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

}
