package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ControlFlowException;

public class MJReturnNode extends MJStatementNode {
    @Child MJExpressionNode returnValue;

    public MJReturnNode(MJExpressionNode returnNode) {
        returnValue = returnNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (returnValue != null) {
            throw new MJReturnException(returnValue.executeGeneric(frame));
        }
        throw new MJReturnException(null);
    }

    public static class MJReturnException extends ControlFlowException {
        public final Object value;

        public MJReturnException(Object value) {
            this.value = value;
        }
    }
}
