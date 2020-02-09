package org.truffle.cs.mj.nodes;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantTypeDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class MJArrayNode {
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    @NodeChild(value = "index", type = MJExpressionNode.class)
    public static abstract class MJReadArrayElement extends MJExpressionNode {

        protected abstract FrameSlot getSlot();

        @Override
        public abstract TypeDescriptor getType();

        @ExplodeLoop
        private VirtualFrame getFrame(VirtualFrame frame) {
            CompilerDirectives.transferToInterpreter();
            while (!frame.getFrameDescriptor().getSlots().contains(getSlot())) {
                frame = (VirtualFrame) frame.getArguments()[0];
            }

            return frame;
        }

        @Specialization(guards = "isBoolVariable(getType())")
        public Object readBoolVariable(VirtualFrame frame, int index) {
            try {
                FrameSlot frameSlot = getSlot();
                ArrayList<Boolean> a = (ArrayList<Boolean>) getFrame(frame).getObject(frameSlot);
                return a.get(index);
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isCharVariable(getType())")
        public Object readCharVariable(VirtualFrame frame, int index) {
            try {
                FrameSlot frameSlot = getSlot();
                ArrayList<Character> a = (ArrayList<Character>) getFrame(frame).getObject(frameSlot);
                return a.get(index);
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isIntVariable(getType())")
        public Object readIntVariable(VirtualFrame frame, int index) {
            try {
                FrameSlot frameSlot = getSlot();
                ArrayList<Integer> a = (ArrayList<Integer>) getFrame(frame).getObject(frameSlot);
                return a.get(index);
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "isDoubleVariable(getType())")
        public Object readDoubleVariable(VirtualFrame frame, int index) {
            try {
                FrameSlot frameSlot = getSlot();
                ArrayList<Double> a = (ArrayList<Double>) getFrame(frame).getObject(frameSlot);
                return a.get(index);
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization
        public Object readVariable(VirtualFrame frame, int index) {
            try {
                FrameSlot frameSlot = getSlot();
                ArrayList<Object> a = (ArrayList<Object>) getFrame(frame).getObject(frameSlot);
                return a.get(index);
            } catch (FrameSlotTypeException e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

    }

    @NodeChild(value = "value", type = MJExpressionNode.class)
    @NodeChild(value = "index", type = MJExpressionNode.class)
    @NodeField(name = "slot", type = FrameSlot.class)
    @NodeField(name = "type", type = TypeDescriptor.class)
    public static abstract class MJWriteArrayElement extends MJStatementNode {
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
        public Object execute(VirtualFrame frame, boolean value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Boolean> a;
            try {
                try {
                    a = (ArrayList<Boolean>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = {"isCharVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, char value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Character> a;
            try {
                try {
                    a = (ArrayList<Character>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = {"isDoubleVariable(getType())", "!isConstant(getType())"})
        public Object executeImplicitDouble(VirtualFrame frame, int value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Double> a;
            try {
                try {
                    a = (ArrayList<Double>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, (double) value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = {"isIntVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, int value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Integer> a;
            try {
                try {
                    a = (ArrayList<Integer>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = {"isDoubleVariable(getType())", "!isConstant(getType())"})
        public Object execute(VirtualFrame frame, double value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Double> a;
            try {
                try {
                    a = (ArrayList<Double>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }

        @Specialization(guards = "!isConstant(getType())")
        public Object execute(VirtualFrame frame, Object value, int index) {
            FrameSlot frameSlot = getSlot();
            ArrayList<Object> a;
            try {
                try {
                    a = (ArrayList<Object>) getFrame(frame).getObject(frameSlot);
                } catch (FrameSlotTypeException e) {
                    a = new ArrayList<>();
                }
                a.set(index, value);
                getFrame(frame).setObject(getSlot(), a);
                return null;
            } catch (Exception e) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                e.printStackTrace();
                throw new Error(e);
            }
        }
    }
}
