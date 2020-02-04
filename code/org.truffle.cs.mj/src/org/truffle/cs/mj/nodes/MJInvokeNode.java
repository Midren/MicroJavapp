package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class MJInvokeNode extends MJExpressionNode {
    @Child private DirectCallNode callNode;
    @Children private final MJExpressionNode[] params;

    public MJInvokeNode(CallTarget target, MJExpressionNode[] params) {
        callNode = DirectCallNode.create(target);
        this.params = params;
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame) {

        Object[] argumentValuesObjects = new Object[params.length];
        for (int i = 0; i < argumentValuesObjects.length; i++) {
            argumentValuesObjects[i] = params[i].executeGeneric(frame);
        }
        return callNode.call(argumentValuesObjects);
    }

}
