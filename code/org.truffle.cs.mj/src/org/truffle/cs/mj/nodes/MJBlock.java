package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class MJBlock extends MJStatementNode {
    @Children MJStatementNode statements[];

    private final FrameDescriptor frameDescriptor;

    public MJBlock(MJStatementNode[] statements, FrameDescriptor descriptor) {
        this.statements = statements;
        this.frameDescriptor = descriptor;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        VirtualFrame innerFrame = Truffle.getRuntime().createVirtualFrame(new Object[]{frame}, frameDescriptor);
        for (MJStatementNode stat : statements) {
            stat.execute(innerFrame);
        }
        return null;
    }

}
