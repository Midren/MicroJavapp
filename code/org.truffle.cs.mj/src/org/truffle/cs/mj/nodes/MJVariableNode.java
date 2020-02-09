package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.TypeTable;
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
import com.oracle.truffle.api.dsl.ImportStatic;
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

        @Specialization(guards = "isBoolVariable(getType())")
        public Object readBoolVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getBoolean(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isCharVariable(getType())")
        public Object readCharVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getByte(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isIntVariable(getType())")
        public Object readIntVariable(VirtualFrame frame) {
            try {
                return getFrame(frame).getInt(getSlot());
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isDoubleVariable(getType())")
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
    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    @ImportStatic(MJExpressionNode.class)
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

        @Specialization(guards = {"isBoolVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, boolean value) {
            getFrame(frame).setBoolean(getSlot(), value);
            return null;
        }

        @Specialization(guards = {"isCharVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setByte(getSlot(), (byte) value);
            return null;
        }

        @Specialization(guards = {"isDoubleVariable(getType())", "!isConstant(getType())"})
        public Object executeImplicitDouble(VirtualFrame frame, int value) {
            getFrame(frame).setDouble(getSlot(), (double) value);
            return null;
        }

        @Specialization(guards = {"isIntVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setInt(getSlot(), value);
            return null;
        }

        @Specialization(guards = {"isDoubleVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setDouble(getSlot(), value);
            return null;
        }

        @Specialization(guards = "!isConstant(getType())")
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }
    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    @ImportStatic(MJExpressionNode.class)
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

        @Specialization(guards = "isBoolVariable(getType())")
        public Object execute(VirtualFrame frame, boolean value) {
            getFrame(frame).setBoolean(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isCharVariable(getType())")
        public Object execute(VirtualFrame frame, char value) {
            getFrame(frame).setByte(getSlot(), (byte) value);
            return null;
        }

        @Specialization(guards = "isIntVariable(getType())")
        public Object execute(VirtualFrame frame, int value) {
            getFrame(frame).setInt(getSlot(), value);
            return null;
        }

        @Specialization(guards = "isDoubleVariable(getType())")
        public Object execute(VirtualFrame frame, double value) {
            getFrame(frame).setDouble(getSlot(), value);
            return null;
        }

        @Specialization
        public Object execute(VirtualFrame frame, Object value) {
            getFrame(frame).setObject(getSlot(), value);
            return null;
        }
    }
}
