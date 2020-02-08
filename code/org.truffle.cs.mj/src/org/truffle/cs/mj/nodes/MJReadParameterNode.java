package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo
public class MJReadParameterNode extends MJExpressionNode {
    final int index;
    final TypeDescriptor type;

    @Override
    public TypeDescriptor getType() {
        return type;
    }

    public MJReadParameterNode(int index, TypeDescriptor typeDescriptor) {
        this.index = index + 1;
        this.type = typeDescriptor;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (getFrameWithArguments(frame).getArguments()[0] == null)
            return getFrameWithArguments(frame).getArguments()[index];
        else
            return getFrameWithArguments(frame).getArguments()[index - 1];
    }

    private VirtualFrame getFrameWithArguments(VirtualFrame frame) {
        while (frame.getArguments().length == 1 && frame.getArguments()[0] instanceof VirtualFrame) {
            frame = (VirtualFrame) frame.getArguments()[0];
        }
        return frame;
    }
}
