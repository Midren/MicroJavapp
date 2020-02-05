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
            if ((state & 0b10) == 0 /* only-active add(int, int) */ && state != 0  /* is-not add(int, int) && add(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active add(int, int) */;
            return add(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active add(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return add(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active add(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return add(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new AssertionError("Delegation failed.");
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if ((state & 0b1) != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return (int) executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private Object executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active add(int, int) */;
                    return add(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active add(double, double) */;
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
            } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
                return NodeCost.MONOMORPHIC;
            }
            return NodeCost.POLYMORPHIC;
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
            if ((state & 0b10) == 0 /* only-active subtract(int, int) */ && state != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active subtract(int, int) */;
            return subtract(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active subtract(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return subtract(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active subtract(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return subtract(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new AssertionError("Delegation failed.");
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if ((state & 0b1) != 0 /* is-active subtract(int, int) */) {
                return subtract(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return (int) executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private Object executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active subtract(int, int) */;
                    return subtract(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active subtract(double, double) */;
                    return subtract(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active multiply(int, int) */ && state != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active multiply(int, int) */;
            return multiply(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active multiply(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return multiply(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active multiply(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return multiply(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new AssertionError("Delegation failed.");
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if ((state & 0b1) != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return (int) executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private Object executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active multiply(int, int) */;
                    return multiply(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active multiply(double, double) */;
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
            } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
                return NodeCost.MONOMORPHIC;
            }
            return NodeCost.POLYMORPHIC;
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
            if ((state & 0b10) == 0 /* only-active divide(int, int) */ && state != 0  /* is-not divide(int, int) && divide(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active divide(int, int) */;
            return divide(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active divide(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return divide(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active divide(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return divide(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new AssertionError("Delegation failed.");
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if ((state & 0b1) != 0 /* is-active divide(int, int) */) {
                return divide(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return (int) executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private Object executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active divide(int, int) */;
                    return divide(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active divide(double, double) */;
                    return divide(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active modulation(int, int) */ && state != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active modulation(int, int) */;
            return modulation(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active modulation(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return modulation(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active modulation(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return modulation(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new AssertionError("Delegation failed.");
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            int state = state_;
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            if ((state & 0b1) != 0 /* is-active modulation(int, int) */) {
                return modulation(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return (int) executeAndSpecialize(lhsValue_, rhsValue_);
        }

        private Object executeAndSpecialize(Object lhsValue, Object rhsValue) {
            int state = state_;
            if (lhsValue instanceof Integer) {
                int lhsValue_ = (int) lhsValue;
                if (rhsValue instanceof Integer) {
                    int rhsValue_ = (int) rhsValue;
                    this.state_ = state = state | 0b1 /* add-active modulation(int, int) */;
                    return modulation(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active modulation(double, double) */;
                    return modulation(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b1110) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
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
            if ((state & 0b10) != 0 /* is-active equal(char, char) */ && lhsValue_ instanceof Character) {
                char lhsValue__ = (char) lhsValue_;
                if (rhsValue_ instanceof Character) {
                    char rhsValue__ = (char) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b100) != 0 /* is-active equal(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b1000) != 0 /* is-active equal(Object, Object) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b1110) == 0 /* only-active equal(int, int) */ && state != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
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
            if ((state & 0b10) != 0 /* is-active equal(char, char) */ && lhsValue_ instanceof Character) {
                char lhsValue__ = (char) lhsValue_;
                if (rhsValue_ instanceof Character) {
                    char rhsValue__ = (char) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b100) != 0 /* is-active equal(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b1000) != 0 /* is-active equal(Object, Object) */) {
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
            if (lhsValue instanceof Character) {
                char lhsValue_ = (char) lhsValue;
                if (rhsValue instanceof Character) {
                    char rhsValue_ = (char) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active equal(char, char) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b100 /* add-active equal(double, double) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            this.state_ = state = state | 0b1000 /* add-active equal(Object, Object) */;
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
            if ((state & 0b1110) == 0 /* only-active notEqual(int, int) */ && state != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active notEqual(int, int) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active notEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return notEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(char, char) */ && lhsValue_ instanceof Character) {
                char lhsValue__ = (char) lhsValue_;
                if (rhsValue_ instanceof Character) {
                    char rhsValue__ = (char) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b100) != 0 /* is-active notEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return notEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b1000) != 0 /* is-active notEqual(Object, Object) */) {
                return notEqual(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b1110) == 0 /* only-active notEqual(int, int) */ && state != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active notEqual(int, int) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active notEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return notEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active equal(char, char) */ && lhsValue_ instanceof Character) {
                char lhsValue__ = (char) lhsValue_;
                if (rhsValue_ instanceof Character) {
                    char rhsValue__ = (char) rhsValue_;
                    return equal(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b100) != 0 /* is-active notEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return notEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b1000) != 0 /* is-active notEqual(Object, Object) */) {
                return notEqual(lhsValue_, rhsValue_);
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
                    this.state_ = state = state | 0b1 /* add-active notEqual(int, int) */;
                    return notEqual(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Character) {
                char lhsValue_ = (char) lhsValue;
                if (rhsValue instanceof Character) {
                    char rhsValue_ = (char) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active equal(char, char) */;
                    return equal(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b100 /* add-active notEqual(double, double) */;
                    return notEqual(lhsValue_, rhsValue_);
                }
            }
            this.state_ = state = state | 0b1000 /* add-active notEqual(Object, Object) */;
            return notEqual(lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active less(int, int) */ && state != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active less(int, int) */;
            return less(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active less(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active less(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active less(int, int) */ && state != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active less(int, int) */;
            return less(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active less(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active less(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
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
                    this.state_ = state = state | 0b1 /* add-active less(int, int) */;
                    return less(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active less(double, double) */;
                    return less(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active lessEqual(int, int) */ && state != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active lessEqual(int, int) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active lessEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active lessEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active lessEqual(int, int) */ && state != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active lessEqual(int, int) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active lessEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active lessEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
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
                    this.state_ = state = state | 0b1 /* add-active lessEqual(int, int) */;
                    return lessEqual(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active lessEqual(double, double) */;
                    return lessEqual(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active more(int, int) */ && state != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active more(int, int) */;
            return more(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active more(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active more(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active more(int, int) */ && state != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active more(int, int) */;
            return more(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active more(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active more(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
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
                    this.state_ = state = state | 0b1 /* add-active more(int, int) */;
                    return more(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active more(double, double) */;
                    return more(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
            if ((state & 0b10) == 0 /* only-active moreEqual(int, int) */ && state != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else {
                return executeGeneric_generic1(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active moreEqual(int, int) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic1(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active moreEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active moreEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active moreEqual(int, int) */ && state != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeBool_int_int2(frameValue, state);
            } else {
                return executeBool_generic3(frameValue, state);
            }
        }

        private boolean executeBool_int_int2(VirtualFrame frameValue, int state) {
            int lhsValue_ = this.lhs_.executeI32(frameValue);
            int rhsValue_ = this.rhs_.executeI32(frameValue);
            assert (state & 0b1) != 0 /* is-active moreEqual(int, int) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic3(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active moreEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active moreEqual(double, double) */ && lhsValue_ instanceof Double) {
                double lhsValue__ = (double) lhsValue_;
                if (rhsValue_ instanceof Double) {
                    double rhsValue__ = (double) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
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
                    this.state_ = state = state | 0b1 /* add-active moreEqual(int, int) */;
                    return moreEqual(lhsValue_, rhsValue_);
                }
            }
            if (lhsValue instanceof Double) {
                double lhsValue_ = (double) lhsValue;
                if (rhsValue instanceof Double) {
                    double rhsValue_ = (double) rhsValue;
                    this.state_ = state = state | 0b10 /* add-active moreEqual(double, double) */;
                    return moreEqual(lhsValue_, rhsValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
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
