package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

public class MJVariableNode {
    @NodeField(name = "slot", type = FrameSlot.class)
    public static abstract class MJReadLocalVariableNode extends MJExpressionNode {
        protected abstract FrameSlot getSlot();

        @Specialization
        public Object readVariable(VirtualFrame frame) {
            try {
                return frame.getObject(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }
    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    public static abstract class MJWriteLocalVariableNode extends MJStatementNode {
        protected abstract FrameSlot getSlot();

        @Specialization
        public Object execute(VirtualFrame frame, Object value) {
            // TODO: Optimize for different types
            frame.setObject(getSlot(), value);
            return null;
        }
    }
}
