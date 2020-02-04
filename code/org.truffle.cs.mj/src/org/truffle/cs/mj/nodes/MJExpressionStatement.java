package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MJExpressionStatement extends MJStatementNode {
    @Child MJExpressionNode expr;

    public MJExpressionStatement(MJExpressionNode expr) {
        this.expr = expr;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        expr.executeGeneric(frame);
        return null;
    }

}
