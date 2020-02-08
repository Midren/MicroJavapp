package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantCharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantDoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantIntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

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
                FrameSlot frameSlot = getSlot();

                Object a = getFrame(frame).getObject(frameSlot);
// System.out.println(frameSlot.toString() + a);
                return a;
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @ExplodeLoop
        private VirtualFrame getFrame(VirtualFrame frame) {
            CompilerDirectives.transferToInterpreter();
            while (!frame.getFrameDescriptor().getSlots().contains(getSlot())) {
                frame = (VirtualFrame) frame.getArguments()[0];
            }

            return frame;
        }
    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    public static abstract class MJWriteLocalVariableNode extends MJStatementNode {
        protected abstract FrameSlot getSlot();

        protected abstract TypeDescriptor getType();

        @ExplodeLoop
        private VirtualFrame getFrame(VirtualFrame frame) {
            CompilerDirectives.transferToInterpreter();
            while (!frame.getFrameDescriptor().getSlots().contains(getSlot())) {
                frame = (VirtualFrame) frame.getArguments()[0];
            }

            return frame;
        }

        @Specialization(guards = "isCharVariable()")
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isIntVariable()")
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isDoubleVariable()")
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isNotPrimitive()")
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        protected final boolean isCharVariable() {
            return getType() == CharDescriptor.getInstance();
        }

        protected final boolean isIntVariable() {
            return getType() == IntDescriptor.getInstance();
        }

        protected final boolean isDoubleVariable() {
            return getType() == DoubleDescriptor.getInstance();
        }

        protected final boolean isNotPrimitive() {
            TypeDescriptor type = getType();
            return type != CharDescriptor.getInstance() && type != IntDescriptor.getInstance() && type != DoubleDescriptor.getInstance();
        }
    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    public static abstract class MJWriteConstantLocalVariableNode extends MJStatementNode {
        protected abstract FrameSlot getSlot();

        protected abstract TypeDescriptor getType();

        @ExplodeLoop
        private VirtualFrame getFrame(VirtualFrame frame) {
            CompilerDirectives.transferToInterpreter();
            while (!frame.getFrameDescriptor().getSlots().contains(getSlot())) {
                frame = (VirtualFrame) frame.getArguments()[0];
            }

            return frame;
        }

        @Specialization(guards = "isCharVariable()")
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isIntVariable()")
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isDoubleVariable()")
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isNotPrimitive()")
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        protected final boolean isCharVariable() {
            return getType() == ConstantCharDescriptor.getInstance();
        }

        protected final boolean isIntVariable() {
            return getType() == ConstantIntDescriptor.getInstance();
        }

        protected final boolean isDoubleVariable() {
            return getType() == ConstantDoubleDescriptor.getInstance();
        }

        protected final boolean isNotPrimitive() {
            TypeDescriptor type = getType();
            return type != ConstantCharDescriptor.getInstance() && type != ConstantIntDescriptor.getInstance() && type != ConstantDoubleDescriptor.getInstance();
        }
    }
}
