package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MJConditionalNode extends MJStatementNode {
    @Child MJExpressionNode condition;
    @Child MJStatementNode trueCase;
    @Child MJStatementNode falseCase;

    public MJConditionalNode(MJExpressionNode condition, MJStatementNode trueCase, MJStatementNode falseCase) {
        super();
        this.condition = condition;
        this.trueCase = trueCase;
        this.falseCase = falseCase;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (condition.executeBool(frame)) {
            trueCase.execute(frame);
        } else {
            if (falseCase != null) {
                falseCase.execute(frame);
            }
        }
        return null;
    }
}
