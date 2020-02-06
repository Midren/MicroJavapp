// CheckStyle: start generated
package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJReadLocalVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJWriteLocalVariableNode;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

@GeneratedBy(MJVariableNode.class)
public final class MJVariableNodeFactory {

    @GeneratedBy(MJReadLocalVariableNode.class)
    public static final class MJReadLocalVariableNodeGen extends MJReadLocalVariableNode {

        private final FrameSlot slot;
        private final TypeDescriptor type;

        private MJReadLocalVariableNodeGen(FrameSlot slot, TypeDescriptor type) {
            this.slot = slot;
            this.type = type;
        }

        @Override
        protected FrameSlot getSlot() {
            return this.slot;
        }

        @Override
        public TypeDescriptor getType() {
            return this.type;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return readVariable(frameValue);
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.MONOMORPHIC;
        }

        public static MJReadLocalVariableNode create(FrameSlot slot, TypeDescriptor type) {
            return new MJReadLocalVariableNodeGen(slot, type);
        }

    }
    @GeneratedBy(MJWriteLocalVariableNode.class)
    public static final class MJWriteLocalVariableNodeGen extends MJWriteLocalVariableNode {

        private final FrameSlot slot;
        private final TypeDescriptor type;
        @Child private MJExpressionNode value_;
        @CompilationFinal private int state_;

        private MJWriteLocalVariableNodeGen(MJExpressionNode value, FrameSlot slot, TypeDescriptor type) {
            this.slot = slot;
            this.type = type;
            this.value_ = value;
        }

        @Override
        protected FrameSlot getSlot() {
            return this.slot;
        }

        @Override
        protected TypeDescriptor getType() {
            return this.type;
        }

        @Override
        public Object execute(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b1110) == 0 /* only-active execute(VirtualFrame, char) */ && state != 0  /* is-not execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_char0(frameValue, state);
            } else if ((state & 0b1101) == 0 /* only-active execute(VirtualFrame, int) */ && state != 0  /* is-not execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_int1(frameValue, state);
            } else if ((state & 0b1011) == 0 /* only-active execute(VirtualFrame, double) */ && state != 0  /* is-not execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_double2(frameValue, state);
            } else {
                return execute_generic3(frameValue, state);
            }
        }

        private Object execute_char0(VirtualFrame frameValue, int state) {
            char valueValue_;
            try {
                valueValue_ = this.value_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active execute(VirtualFrame, char) */;
            assert (isCharVariable());
            return execute(frameValue, valueValue_);
        }

        private Object execute_int1(VirtualFrame frameValue, int state) {
            int valueValue_;
            try {
                valueValue_ = this.value_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active execute(VirtualFrame, int) */;
            assert (isIntVariable());
            return execute(frameValue, valueValue_);
        }

        private Object execute_double2(VirtualFrame frameValue, int state) {
            double valueValue_;
            try {
                valueValue_ = this.value_.executeDouble(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active execute(VirtualFrame, double) */;
            assert (isDoubleVariable());
            return execute(frameValue, valueValue_);
        }

        private Object execute_generic3(VirtualFrame frameValue, int state) {
            Object valueValue_ = this.value_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active execute(VirtualFrame, char) */ && valueValue_ instanceof Character) {
                char valueValue__ = (char) valueValue_;
                assert (isCharVariable());
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b10) != 0 /* is-active execute(VirtualFrame, int) */ && valueValue_ instanceof Integer) {
                int valueValue__ = (int) valueValue_;
                assert (isIntVariable());
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b100) != 0 /* is-active execute(VirtualFrame, double) */ && valueValue_ instanceof Double) {
                double valueValue__ = (double) valueValue_;
                assert (isDoubleVariable());
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b1000) != 0 /* is-active execute(VirtualFrame, Object) */) {
                assert (isNotPrimitive());
                return execute(frameValue, valueValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue, valueValue_);
        }

        private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
            int state = state_;
            if (valueValue instanceof Character) {
                char valueValue_ = (char) valueValue;
                if ((isCharVariable())) {
                    this.state_ = state = state | 0b1 /* add-active execute(VirtualFrame, char) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Integer) {
                int valueValue_ = (int) valueValue;
                if ((isIntVariable())) {
                    this.state_ = state = state | 0b10 /* add-active execute(VirtualFrame, int) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Double) {
                double valueValue_ = (double) valueValue;
                if ((isDoubleVariable())) {
                    this.state_ = state = state | 0b100 /* add-active execute(VirtualFrame, double) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if ((isNotPrimitive())) {
                this.state_ = state = state | 0b1000 /* add-active execute(VirtualFrame, Object) */;
                return execute(frameValue, valueValue);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
                return NodeCost.MONOMORPHIC;
            }
            return NodeCost.POLYMORPHIC;
        }

        public static MJWriteLocalVariableNode create(MJExpressionNode value, FrameSlot slot, TypeDescriptor type) {
            return new MJWriteLocalVariableNodeGen(value, slot, type);
        }

    }
}
