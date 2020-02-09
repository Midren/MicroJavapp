package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantBoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantCharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantDoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantIntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantTypeDescriptor;

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

        @Specialization(guards = "isBoolVariable()")
        public Object readBoolVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getBoolean(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isCharVariable()")
        public Object readCharVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getByte(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isIntVariable()")
        public Object readIntVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getInt(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isDoubleVariable()")
        public Object readDoubleVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getDouble(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization
        public Object readVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getObject(getSlot());
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

        protected boolean isBoolVariable() {
            return getType() instanceof BoolDescriptor;
        }

        protected final boolean isCharVariable() {
            return getType() instanceof CharDescriptor;
        }

        protected final boolean isIntVariable() {
            return getType() instanceof IntDescriptor;
        }

        protected final boolean isDoubleVariable() {
            return getType() instanceof DoubleDescriptor;
        }

        protected final boolean isNotPrimitive() {
            TypeDescriptor type = getType();
            return !(type instanceof CharDescriptor ||
                            type instanceof BoolDescriptor ||
                            type instanceof IntDescriptor ||
                            type instanceof DoubleDescriptor);
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

        @Specialization(guards = {"isBoolVariable()", "isNotConstant()"})
        public Object execute(VirtualFrame frame, boolean value) {
            getFrame(frame).setBoolean(getSlot(), value);
            return null;
        }

        @Specialization(guards = {"isCharVariable()", "isNotConstant()"})
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setByte(getSlot(), (byte) value);
            return null;
        }

        @Specialization(guards = {"isDoubleVariable()", "isNotConstant()"})
        public Object executeImplicitDouble(VirtualFrame frame, int value) {
            getFrame(frame).setDouble(getSlot(), (double) value);
            return null;
        }

        @Specialization(guards = {"isIntVariable()", "isNotConstant()"})
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setInt(getSlot(), value);
            return null;
        }

        @Specialization(guards = {"isDoubleVariable()", "isNotConstant()"})
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setDouble(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isNotConstant()")
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        protected final boolean isNotConstant() {
            return !(getType() instanceof ConstantTypeDescriptor);
        }

        protected final boolean isBoolVariable() {
            return getType() instanceof CharDescriptor;
        }

        protected final boolean isCharVariable() {
            return getType() instanceof CharDescriptor;
        }

        protected final boolean isIntVariable() {
            return getType() instanceof IntDescriptor;
        }

        protected final boolean isDoubleVariable() {
            return getType() instanceof DoubleDescriptor;
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

        @Specialization(guards = "isBoolVariable()")
        public Object execute(VirtualFrame frame, boolean value) {
            getFrame(frame).setBoolean(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isCharVariable()")
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setByte(getSlot(), (byte) value);
            return null;
        }

        @Specialization(guards = "isIntVariable()")
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setInt(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isDoubleVariable()")
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setDouble(getSlot(), value);
            return null;
        }

        @Specialization
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }

        protected final boolean isBoolVariable() {
            return getType() instanceof BoolDescriptor;
        }

        protected final boolean isCharVariable() {
            return getType() instanceof CharDescriptor;
        }

        protected final boolean isIntVariable() {
            return getType() instanceof IntDescriptor;
        }

        protected final boolean isDoubleVariable() {
            return getType() instanceof DoubleDescriptor;
        }

    }
}
