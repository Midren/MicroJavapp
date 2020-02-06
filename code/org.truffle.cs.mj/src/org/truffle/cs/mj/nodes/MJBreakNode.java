package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ControlFlowException;

public class MJBreakNode extends MJStatementNode {
    public static class MJBreakException extends ControlFlowException {

    }

    @Override
    public Object execute(VirtualFrame frame) {
        throw new MJBreakException();
    }

}