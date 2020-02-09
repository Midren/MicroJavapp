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
import org.truffle.cs.mj.nodes.MJVariableNode.MJWriteConstantLocalVariableNode;
import org.truffle.cs.mj.nodes.MJVariableNode.MJWriteLocalVariableNode;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

@GeneratedBy(MJVariableNode.class)
public final class MJVariableNodeFactory {

    @GeneratedBy(MJReadLocalVariableNode.class)
    public static final class MJReadLocalVariableNodeGen extends MJReadLocalVariableNode {

        private final FrameSlot slot;
        private final TypeDescriptor type;
        @CompilationFinal private int state_;

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
            int state = state_;
            if ((state & 0b1) != 0 /* is-active readBoolVariable(VirtualFrame) */) {
                assert (MJExpressionNode.isBoolVariable(getType()));
                return readBoolVariable(frameValue);
            }
            if ((state & 0b10) != 0 /* is-active readCharVariable(VirtualFrame) */) {
                assert (MJExpressionNode.isCharVariable(getType()));
                return readCharVariable(frameValue);
            }
            if ((state & 0b100) != 0 /* is-active readIntVariable(VirtualFrame) */) {
                assert (MJExpressionNode.isIntVariable(getType()));
                return readIntVariable(frameValue);
            }
            if ((state & 0b1000) != 0 /* is-active readDoubleVariable(VirtualFrame) */) {
                assert (MJExpressionNode.isDoubleVariable(getType()));
                return readDoubleVariable(frameValue);
            }
            if ((state & 0b10000) != 0 /* is-active readVariable(VirtualFrame) */) {
                return readVariable(frameValue);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue);
        }

        private Object executeAndSpecialize(VirtualFrame frameValue) {
            int state = state_;
            if ((MJExpressionNode.isBoolVariable(getType()))) {
                this.state_ = state = state | 0b1 /* add-active readBoolVariable(VirtualFrame) */;
                return readBoolVariable(frameValue);
            }
            if ((MJExpressionNode.isCharVariable(getType()))) {
                this.state_ = state = state | 0b10 /* add-active readCharVariable(VirtualFrame) */;
                return readCharVariable(frameValue);
            }
            if ((MJExpressionNode.isIntVariable(getType()))) {
                this.state_ = state = state | 0b100 /* add-active readIntVariable(VirtualFrame) */;
                return readIntVariable(frameValue);
            }
            if ((MJExpressionNode.isDoubleVariable(getType()))) {
                this.state_ = state = state | 0b1000 /* add-active readDoubleVariable(VirtualFrame) */;
                return readDoubleVariable(frameValue);
            }
            this.state_ = state = state | 0b10000 /* add-active readVariable(VirtualFrame) */;
            return readVariable(frameValue);
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
            if ((state & 0b111110) == 0 /* only-active execute(VirtualFrame, boolean) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && executeImplicitDouble(VirtualFrame, int) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_boolean0(frameValue, state);
            } else if ((state & 0b111101) == 0 /* only-active execute(VirtualFrame, char) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && executeImplicitDouble(VirtualFrame, int) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_char1(frameValue, state);
            } else if ((state & 0b110011) == 0 /* only-active executeImplicitDouble(VirtualFrame, int) && execute(VirtualFrame, int) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && executeImplicitDouble(VirtualFrame, int) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_int2(frameValue, state);
            } else if ((state & 0b101111) == 0 /* only-active execute(VirtualFrame, double) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && executeImplicitDouble(VirtualFrame, int) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_double3(frameValue, state);
            } else {
                return execute_generic4(frameValue, state);
            }
        }

        private Object execute_boolean0(VirtualFrame frameValue, int state) {
            boolean valueValue_;
            try {
                valueValue_ = this.value_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active execute(VirtualFrame, boolean) */;
            assert (MJExpressionNode.isBoolVariable(getType()));
            assert (!(MJExpressionNode.isConstant(getType())));
            return execute(frameValue, valueValue_);
        }

        private Object execute_char1(VirtualFrame frameValue, int state) {
            char valueValue_;
            try {
                valueValue_ = this.value_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active execute(VirtualFrame, char) */;
            assert (MJExpressionNode.isCharVariable(getType()));
            assert (!(MJExpressionNode.isConstant(getType())));
            return execute(frameValue, valueValue_);
        }

        private Object execute_int2(VirtualFrame frameValue, int state) {
            int valueValue_;
            try {
                valueValue_ = this.value_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            if ((state & 0b100) != 0 /* is-active executeImplicitDouble(VirtualFrame, int) */) {
                assert (MJExpressionNode.isDoubleVariable(getType()));
                assert (!(MJExpressionNode.isConstant(getType())));
                return executeImplicitDouble(frameValue, valueValue_);
            }
            if ((state & 0b1000) != 0 /* is-active execute(VirtualFrame, int) */) {
                assert (MJExpressionNode.isIntVariable(getType()));
                assert (!(MJExpressionNode.isConstant(getType())));
                return execute(frameValue, valueValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue, valueValue_);
        }

        private Object execute_double3(VirtualFrame frameValue, int state) {
            double valueValue_;
            try {
                valueValue_ = this.value_.executeDouble(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b10000) != 0 /* is-active execute(VirtualFrame, double) */;
            assert (MJExpressionNode.isDoubleVariable(getType()));
            assert (!(MJExpressionNode.isConstant(getType())));
            return execute(frameValue, valueValue_);
        }

        private Object execute_generic4(VirtualFrame frameValue, int state) {
            Object valueValue_ = this.value_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active execute(VirtualFrame, boolean) */ && valueValue_ instanceof Boolean) {
                boolean valueValue__ = (boolean) valueValue_;
                assert (MJExpressionNode.isBoolVariable(getType()));
                assert (!(MJExpressionNode.isConstant(getType())));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b10) != 0 /* is-active execute(VirtualFrame, char) */ && valueValue_ instanceof Character) {
                char valueValue__ = (char) valueValue_;
                assert (MJExpressionNode.isCharVariable(getType()));
                assert (!(MJExpressionNode.isConstant(getType())));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b1100) != 0 /* is-active executeImplicitDouble(VirtualFrame, int) || execute(VirtualFrame, int) */ && valueValue_ instanceof Integer) {
                int valueValue__ = (int) valueValue_;
                if ((state & 0b100) != 0 /* is-active executeImplicitDouble(VirtualFrame, int) */) {
                    assert (MJExpressionNode.isDoubleVariable(getType()));
                    assert (!(MJExpressionNode.isConstant(getType())));
                    return executeImplicitDouble(frameValue, valueValue__);
                }
                if ((state & 0b1000) != 0 /* is-active execute(VirtualFrame, int) */) {
                    assert (MJExpressionNode.isIntVariable(getType()));
                    assert (!(MJExpressionNode.isConstant(getType())));
                    return execute(frameValue, valueValue__);
                }
            }
            if ((state & 0b10000) != 0 /* is-active execute(VirtualFrame, double) */ && valueValue_ instanceof Double) {
                double valueValue__ = (double) valueValue_;
                assert (MJExpressionNode.isDoubleVariable(getType()));
                assert (!(MJExpressionNode.isConstant(getType())));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b100000) != 0 /* is-active execute(VirtualFrame, Object) */) {
                assert (!(MJExpressionNode.isConstant(getType())));
                return execute(frameValue, valueValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue, valueValue_);
        }

        private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
            int state = state_;
            if (valueValue instanceof Boolean) {
                boolean valueValue_ = (boolean) valueValue;
                if ((MJExpressionNode.isBoolVariable(getType())) && (!(MJExpressionNode.isConstant(getType())))) {
                    this.state_ = state = state | 0b1 /* add-active execute(VirtualFrame, boolean) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Character) {
                char valueValue_ = (char) valueValue;
                if ((MJExpressionNode.isCharVariable(getType())) && (!(MJExpressionNode.isConstant(getType())))) {
                    this.state_ = state = state | 0b10 /* add-active execute(VirtualFrame, char) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Integer) {
                int valueValue_ = (int) valueValue;
                if ((MJExpressionNode.isDoubleVariable(getType())) && (!(MJExpressionNode.isConstant(getType())))) {
                    this.state_ = state = state | 0b100 /* add-active executeImplicitDouble(VirtualFrame, int) */;
                    return executeImplicitDouble(frameValue, valueValue_);
                }
                if ((MJExpressionNode.isIntVariable(getType())) && (!(MJExpressionNode.isConstant(getType())))) {
                    this.state_ = state = state | 0b1000 /* add-active execute(VirtualFrame, int) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Double) {
                double valueValue_ = (double) valueValue;
                if ((MJExpressionNode.isDoubleVariable(getType())) && (!(MJExpressionNode.isConstant(getType())))) {
                    this.state_ = state = state | 0b10000 /* add-active execute(VirtualFrame, double) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if ((!(MJExpressionNode.isConstant(getType())))) {
                this.state_ = state = state | 0b100000 /* add-active execute(VirtualFrame, Object) */;
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
    @GeneratedBy(MJWriteConstantLocalVariableNode.class)
    public static final class MJWriteConstantLocalVariableNodeGen extends MJWriteConstantLocalVariableNode {

        private final FrameSlot slot;
        private final TypeDescriptor type;
        @Child private MJExpressionNode value_;
        @CompilationFinal private int state_;

        private MJWriteConstantLocalVariableNodeGen(MJExpressionNode value, FrameSlot slot, TypeDescriptor type) {
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
            if ((state & 0b11110) == 0 /* only-active execute(VirtualFrame, boolean) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_boolean0(frameValue, state);
            } else if ((state & 0b11101) == 0 /* only-active execute(VirtualFrame, char) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_char1(frameValue, state);
            } else if ((state & 0b11011) == 0 /* only-active execute(VirtualFrame, int) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_int2(frameValue, state);
            } else if ((state & 0b10111) == 0 /* only-active execute(VirtualFrame, double) */ && state != 0  /* is-not execute(VirtualFrame, boolean) && execute(VirtualFrame, char) && execute(VirtualFrame, int) && execute(VirtualFrame, double) && execute(VirtualFrame, Object) */) {
                return execute_double3(frameValue, state);
            } else {
                return execute_generic4(frameValue, state);
            }
        }

        private Object execute_boolean0(VirtualFrame frameValue, int state) {
            boolean valueValue_;
            try {
                valueValue_ = this.value_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active execute(VirtualFrame, boolean) */;
            assert (MJExpressionNode.isBoolVariable(getType()));
            return execute(frameValue, valueValue_);
        }

        private Object execute_char1(VirtualFrame frameValue, int state) {
            char valueValue_;
            try {
                valueValue_ = this.value_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active execute(VirtualFrame, char) */;
            assert (MJExpressionNode.isCharVariable(getType()));
            return execute(frameValue, valueValue_);
        }

        private Object execute_int2(VirtualFrame frameValue, int state) {
            int valueValue_;
            try {
                valueValue_ = this.value_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active execute(VirtualFrame, int) */;
            assert (MJExpressionNode.isIntVariable(getType()));
            return execute(frameValue, valueValue_);
        }

        private Object execute_double3(VirtualFrame frameValue, int state) {
            double valueValue_;
            try {
                valueValue_ = this.value_.executeDouble(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            assert (state & 0b1000) != 0 /* is-active execute(VirtualFrame, double) */;
            assert (MJExpressionNode.isDoubleVariable(getType()));
            return execute(frameValue, valueValue_);
        }

        private Object execute_generic4(VirtualFrame frameValue, int state) {
            Object valueValue_ = this.value_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active execute(VirtualFrame, boolean) */ && valueValue_ instanceof Boolean) {
                boolean valueValue__ = (boolean) valueValue_;
                assert (MJExpressionNode.isBoolVariable(getType()));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b10) != 0 /* is-active execute(VirtualFrame, char) */ && valueValue_ instanceof Character) {
                char valueValue__ = (char) valueValue_;
                assert (MJExpressionNode.isCharVariable(getType()));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b100) != 0 /* is-active execute(VirtualFrame, int) */ && valueValue_ instanceof Integer) {
                int valueValue__ = (int) valueValue_;
                assert (MJExpressionNode.isIntVariable(getType()));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b1000) != 0 /* is-active execute(VirtualFrame, double) */ && valueValue_ instanceof Double) {
                double valueValue__ = (double) valueValue_;
                assert (MJExpressionNode.isDoubleVariable(getType()));
                return execute(frameValue, valueValue__);
            }
            if ((state & 0b10000) != 0 /* is-active execute(VirtualFrame, Object) */) {
                return execute(frameValue, valueValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue, valueValue_);
        }

        private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
            int state = state_;
            if (valueValue instanceof Boolean) {
                boolean valueValue_ = (boolean) valueValue;
                if ((MJExpressionNode.isBoolVariable(getType()))) {
                    this.state_ = state = state | 0b1 /* add-active execute(VirtualFrame, boolean) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Character) {
                char valueValue_ = (char) valueValue;
                if ((MJExpressionNode.isCharVariable(getType()))) {
                    this.state_ = state = state | 0b10 /* add-active execute(VirtualFrame, char) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Integer) {
                int valueValue_ = (int) valueValue;
                if ((MJExpressionNode.isIntVariable(getType()))) {
                    this.state_ = state = state | 0b100 /* add-active execute(VirtualFrame, int) */;
                    return execute(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof Double) {
                double valueValue_ = (double) valueValue;
                if ((MJExpressionNode.isDoubleVariable(getType()))) {
                    this.state_ = state = state | 0b1000 /* add-active execute(VirtualFrame, double) */;
                    return execute(frameValue, valueValue_);
                }
            }
            this.state_ = state = state | 0b10000 /* add-active execute(VirtualFrame, Object) */;
            return execute(frameValue, valueValue);
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

        public static MJWriteConstantLocalVariableNode create(MJExpressionNode value, FrameSlot slot, TypeDescriptor type) {
            return new MJWriteConstantLocalVariableNodeGen(value, slot, type);
        }

    }
}
