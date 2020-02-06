package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class MJInvokeNode extends MJExpressionNode {
    @Child private DirectCallNode callNode;
    @Children private final MJExpressionNode[] params;
    private TypeDescriptor returnType;

    @Override
    public TypeDescriptor getType() {
        return returnType;
    }

    public MJInvokeNode(CallTarget target, MJExpressionNode[] params, TypeDescriptor returnType) {
        callNode = DirectCallNode.create(target);
        this.params = params;
        this.returnType = returnType;
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
