package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.nodes.MJContinueNode.MJContinueException;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class MJWhileLoop extends MJStatementNode {
    @Child MJExpressionNode condition;
    @Child MJStatementNode loopBody;

    public MJWhileLoop(MJExpressionNode cond, MJStatementNode block) {
        this.condition = cond;
        this.loopBody = block;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        try {
            while (condition.executeBool(frame)) {
                try {
                    loopBody.execute(frame);
                } catch (MJBreakNode.MJBreakException e) {
                    break;
                } catch (MJContinueException e) {
                    continue;
                }
            }
        } catch (UnexpectedResultException e) {
            throw new Error("Condition should be bool");
        }
        return null;
    }

}
