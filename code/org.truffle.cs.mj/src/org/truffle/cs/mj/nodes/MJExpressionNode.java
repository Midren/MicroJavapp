package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class MJExpressionNode extends Node {

    public abstract Object executeGeneric(VirtualFrame frame);

    public int executeI32(VirtualFrame frame) {
        Object resultObject = executeGeneric(frame);
        if (resultObject instanceof Integer) {
            return (int) resultObject;
        }
        CompilerDirectives.transferToInterpreter();
        throw new Error("type mismatch");
    }

    public boolean executeBool(VirtualFrame frame) {
        Object resultObject = executeGeneric(frame);
        if (resultObject instanceof Integer) {
            return (boolean) resultObject;
        }
        CompilerDirectives.transferToInterpreter();
        throw new Error("type mismatch");
    }
}
