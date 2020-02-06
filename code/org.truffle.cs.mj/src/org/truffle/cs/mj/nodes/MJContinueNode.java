package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ControlFlowException;

public class MJContinueNode extends MJStatementNode {
    public static class MJContinueException extends ControlFlowException {

    }

    @Override
    public Object execute(VirtualFrame frame) {
        throw new MJContinueException();
    }

}
