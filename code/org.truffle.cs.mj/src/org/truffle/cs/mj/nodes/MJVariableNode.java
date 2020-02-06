package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

public class MJVariableNode {
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    public static abstract class MJReadLocalVariableNode extends MJExpressionNode {
        protected abstract FrameSlot getSlot();

        @Override
        public abstract TypeDescriptor getType();

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
    @NodeField(name = "type", type = TypeDescriptor.class)
    public static abstract class MJWriteLocalVariableNode extends MJStatementNode {
        protected abstract FrameSlot getSlot();

        protected abstract TypeDescriptor getType();

        @Specialization(guards = "isCharVariable()")
        public Object execute(VirtualFrame frame, char value) {
            frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Byte);
            frame.setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isIntVariable()")
        public Object execute(VirtualFrame frame, int value) {
            frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Int);
            frame.setObject(getSlot(), value);
            return null;
        }

        @Specialization
        public Object execute(VirtualFrame frame, double value) {
            frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Double);
            frame.setObject(getSlot(), value);
            return null;
        }

// @Specialization
// public Object execute(VirtualFrame frame, Object value) {
// frame.setObject(getSlot(), value);
// return null;
// }

        protected final boolean isCharVariable() {
            return getType() == CharDescriptor.getInstance();
        }

        protected final boolean isIntVariable() {
            return getType() == IntDescriptor.getInstance();
        }
    }
}
