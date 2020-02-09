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
import org.truffle.cs.mj.nodes.MJArrayNode;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJArrayNode.MJReadArrayElement;
import org.truffle.cs.mj.nodes.MJArrayNode.MJWriteArrayElement;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

@GeneratedBy(MJArrayNode.class)
@SuppressWarnings("unused")
public final class MJArrayNodeFactory {

    @GeneratedBy(MJReadArrayElement.class)
    public static final class MJReadArrayElementNodeGen extends MJReadArrayElement {

        private final FrameSlot slot;
        private final TypeDescriptor type;
        @Child private MJExpressionNode index_;
        @CompilationFinal private int state_;

        private MJReadArrayElementNodeGen(MJExpressionNode index, FrameSlot slot, TypeDescriptor type) {
            this.slot = slot;
            this.type = type;
            this.index_ = index;
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
            int indexValue_;
            try {
                indexValue_ = this.index_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(frameValue, ex.getResult());
            }
            if ((state & 0b1) != 0 /* is-active readBoolVariable(VirtualFrame, int) */) {
                assert (MJExpressionNode.isBoolVariable(getType()));
                return readBoolVariable(frameValue, indexValue_);
            }
            if ((state & 0b10) != 0 /* is-active readCharVariable(VirtualFrame, int) */) {
                assert (MJExpressionNode.isCharVariable(getType()));
                return readCharVariable(frameValue, indexValue_);
            }
            if ((state & 0b100) != 0 /* is-active readIntVariable(VirtualFrame, int) */) {
                assert (MJExpressionNode.isIntVariable(getType()));
                return readIntVariable(frameValue, indexValue_);
            }
            if ((state & 0b1000) != 0 /* is-active readDoubleVariable(VirtualFrame, int) */) {
                assert (MJExpressionNode.isDoubleVariable(getType()));
                return readDoubleVariable(frameValue, indexValue_);
            }
            if ((state & 0b10000) != 0 /* is-active readVariable(VirtualFrame, int) */) {
                return readVariable(frameValue, indexValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(frameValue, indexValue_);
        }

        private Object executeAndSpecialize(VirtualFrame frameValue, Object indexValue) {
            int state = state_;
            if (indexValue instanceof Integer) {
                int indexValue_ = (int) indexValue;
                if ((MJExpressionNode.isBoolVariable(getType()))) {
                    this.state_ = state = state | 0b1 /* add-active readBoolVariable(VirtualFrame, int) */;
                    return readBoolVariable(frameValue, indexValue_);
                }
                if ((MJExpressionNode.isCharVariable(getType()))) {
                    this.state_ = state = state | 0b10 /* add-active readCharVariable(VirtualFrame, int) */;
                    return readCharVariable(frameValue, indexValue_);
                }
                if ((MJExpressionNode.isIntVariable(getType()))) {
                    this.state_ = state = state | 0b100 /* add-active readIntVariable(VirtualFrame, int) */;
                    return readIntVariable(frameValue, indexValue_);
                }
                if ((MJExpressionNode.isDoubleVariable(getType()))) {
                    this.state_ = state = state | 0b1000 /* add-active readDoubleVariable(VirtualFrame, int) */;
                    return readDoubleVariable(frameValue, indexValue_);
                }
                this.state_ = state = state | 0b10000 /* add-active readVariable(VirtualFrame, int) */;
                return readVariable(frameValue, indexValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.index_}, indexValue);
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

        public static MJReadArrayElement create(MJExpressionNode index, FrameSlot slot, TypeDescriptor type) {
            return new MJReadArrayElementNodeGen(index, slot, type);
        }

    }
    @GeneratedBy(MJWriteArrayElement.class)
    public static final class MJWriteArrayElementNodeGen extends MJWriteArrayElement {

        private MJWriteArrayElementNodeGen(MJExpressionNode value, MJExpressionNode index) {
        }

        @Override
        public Object execute(VirtualFrame frame) {
            throw new RuntimeException("Truffle DSL compiler errors: [ERROR: Error parsing expression 'isBoolVariable(getType())': The method isBoolVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isCharVariable(getType())': The method isCharVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isIntVariable(getType())': The method isIntVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope.]");
        }

        @Override
        protected FrameSlot getSlot() {
            throw new RuntimeException("Truffle DSL compiler errors: [ERROR: Error parsing expression 'isBoolVariable(getType())': The method isBoolVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isCharVariable(getType())': The method isCharVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isIntVariable(getType())': The method isIntVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope.]");
        }

        @Override
        protected TypeDescriptor getType() {
            throw new RuntimeException("Truffle DSL compiler errors: [ERROR: Error parsing expression 'isBoolVariable(getType())': The method isBoolVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isCharVariable(getType())': The method isCharVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isIntVariable(getType())': The method isIntVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression 'isDoubleVariable(getType())': The method isDoubleVariable is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope., ERROR: Error parsing expression '!isConstant(getType())': The method isConstant is undefined for the enclosing scope.]");
        }

        public static MJWriteArrayElement create(MJExpressionNode value, MJExpressionNode index) {
            return new MJWriteArrayElementNodeGen(value, index);
        }

    }
}
