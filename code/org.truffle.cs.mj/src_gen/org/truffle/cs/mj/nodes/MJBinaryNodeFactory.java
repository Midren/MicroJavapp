// CheckStyle: start generated
package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import org.truffle.cs.mj.nodes.MJBinaryNode;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.AddNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.AndNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.DividerNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.EqualNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.GreaterEqualNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.GreaterNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.LessEqualNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.LessNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.ModulationNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.MultiplicationNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.NotEqualNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.OrNode;
import org.truffle.cs.mj.nodes.MJBinaryNode.SubtractNode;

@GeneratedBy(MJBinaryNode.class)
public final class MJBinaryNodeFactory {

    @GeneratedBy(AddNode.class)
    public static final class AddNodeGen extends AddNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private AddNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private int executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active add(int, int) */;
                    return add(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static AddNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new AddNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(SubtractNode.class)
    public static final class SubtractNodeGen extends SubtractNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private SubtractNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private int executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active add(int, int) */;
                    return add(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static SubtractNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new SubtractNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(MultiplicationNode.class)
    public static final class MultiplicationNodeGen extends MultiplicationNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private MultiplicationNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private int executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active multiply(int, int) */;
                    return multiply(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static MultiplicationNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new MultiplicationNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(DividerNode.class)
    public static final class DividerNodeGen extends DividerNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private DividerNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private int executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active multiply(int, int) */;
                    return multiply(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static DividerNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new DividerNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(ModulationNode.class)
    public static final class ModulationNodeGen extends ModulationNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private ModulationNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private int executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active multiply(int, int) */;
                    return multiply(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static ModulationNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new ModulationNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(EqualNode.class)
    public static final class EqualNodeGen extends EqualNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private EqualNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(Object, Object) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active equal(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(Object, Object) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(Object, Object) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active equal(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(Object, Object) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            this.state_ = state = state | 0b10 /* add-active equal(Object, Object) */;
            return equal(lhsValue, rhsValue);
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

        public static EqualNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new EqualNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(NotEqualNode.class)
    public static final class NotEqualNodeGen extends NotEqualNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private NotEqualNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(Object, Object) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active equal(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(Object, Object) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(Object, Object) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active equal(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(Object, Object) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            this.state_ = state = state | 0b10 /* add-active equal(Object, Object) */;
            return equal(lhsValue, rhsValue);
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

        public static NotEqualNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new NotEqualNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(LessNode.class)
    public static final class LessNodeGen extends LessNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private LessNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static LessNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new LessNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(LessEqualNode.class)
    public static final class LessEqualNodeGen extends LessEqualNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private LessEqualNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static LessEqualNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new LessEqualNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(GreaterNode.class)
    public static final class GreaterNodeGen extends GreaterNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private GreaterNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static GreaterNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new GreaterNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(GreaterEqualNode.class)
    public static final class GreaterEqualNodeGen extends GreaterEqualNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private GreaterEqualNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if (state != 0 /* is-active equal(int, int) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(int, int) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static GreaterEqualNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new GreaterEqualNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(OrNode.class)
    public static final class OrNodeGen extends OrNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private OrNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_ = this.lhs_.executeBool(frameValue);
            boolean rhsValue_ = this.rhs_.executeBool(frameValue);
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_ = this.lhs_.executeBool(frameValue);
            boolean rhsValue_ = this.rhs_.executeBool(frameValue);
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Boolean) {
                boolean lhsValue_ = (boolean) lhsValue;
                if (rhsValue instanceof Boolean) {
                    boolean rhsValue_ = (boolean) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(boolean, boolean) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static OrNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new OrNodeGen(lhs, rhs);
        }

    }
    @GeneratedBy(AndNode.class)
    public static final class AndNodeGen extends AndNode {

        @Child private MJExpressionNode lhs_;
        @Child private MJExpressionNode rhs_;
        @CompilationFinal private int state_;

        private AndNodeGen(MJExpressionNode lhs, MJExpressionNode rhs) {
            this.lhs_ = lhs;
            this.rhs_ = rhs;
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_ = this.lhs_.executeBool(frameValue);
            boolean rhsValue_ = this.rhs_.executeBool(frameValue);
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_ = this.lhs_.executeBool(frameValue);
            boolean rhsValue_ = this.rhs_.executeBool(frameValue);
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private boolean executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Boolean) {
                boolean lhsValue_ = (boolean) lhsValue;
                if (rhsValue instanceof Boolean) {
                    boolean rhsValue_ = (boolean) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active equal(boolean, boolean) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if (state == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else {
                return NodeCost.MONOMORPHIC;
            }
        }

        public static AndNode create(MJExpressionNode lhs, MJExpressionNode rhs) {
            return new AndNodeGen(lhs, rhs);
        }

    }
}
