// CheckStyle: start generated
package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.truffle.cs.mj.nodes.MJBinaryNode;
import org.truffle.cs.mj.nodes.MJExpressionNode;
import org.truffle.cs.mj.nodes.MJTypes;
import org.truffle.cs.mj.nodes.MJTypesGen;
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
            if ((state & 0b10) == 0 /* only-active add(int, int) */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active add(double, double) */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active add(int, int) */;
            return add(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active add(double, double) */;
            return add(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active add(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return add(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active add(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return add(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectDouble(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult()));
            }
            if ((state & 0b10) != 0 /* is-active add(double, double) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not add(int, int) && add(double, double) */ ? (Object) rhsValue_int : (Object) rhsValue_)));
        }

        @Override
        public int executeI32(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectInteger(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, ex.getResult()));
            }
            if ((state & 0b1) != 0 /* is-active add(int, int) */) {
                return add(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, rhsValue_));
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active add(double, double) */;
                        return add(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active subtract(int, int) */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active subtract(double, double) */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active subtract(int, int) */;
            return subtract(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active subtract(double, double) */;
            return subtract(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active subtract(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return subtract(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active subtract(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return subtract(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectDouble(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult()));
            }
            if ((state & 0b10) != 0 /* is-active subtract(double, double) */) {
                return subtract(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not subtract(int, int) && subtract(double, double) */ ? (Object) rhsValue_int : (Object) rhsValue_)));
        }

        @Override
        public int executeI32(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectInteger(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, ex.getResult()));
            }
            if ((state & 0b1) != 0 /* is-active subtract(int, int) */) {
                return subtract(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, rhsValue_));
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active subtract(double, double) */;
                        return subtract(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active multiply(int, int) */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active multiply(double, double) */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active multiply(int, int) */;
            return multiply(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active multiply(double, double) */;
            return multiply(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active multiply(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return multiply(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active multiply(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return multiply(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectDouble(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult()));
            }
            if ((state & 0b10) != 0 /* is-active multiply(double, double) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not multiply(int, int) && multiply(double, double) */ ? (Object) rhsValue_int : (Object) rhsValue_)));
        }

        @Override
        public int executeI32(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectInteger(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, ex.getResult()));
            }
            if ((state & 0b1) != 0 /* is-active multiply(int, int) */) {
                return multiply(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, rhsValue_));
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active multiply(double, double) */;
                        return multiply(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active divide(int, int) */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active divide(double, double) */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active divide(int, int) */;
            return divide(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active divide(double, double) */;
            return divide(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active divide(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return divide(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active divide(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return divide(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectDouble(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult()));
            }
            if ((state & 0b10) != 0 /* is-active divide(double, double) */) {
                return divide(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not divide(int, int) && divide(double, double) */ ? (Object) rhsValue_int : (Object) rhsValue_)));
        }

        @Override
        public int executeI32(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectInteger(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, ex.getResult()));
            }
            if ((state & 0b1) != 0 /* is-active divide(int, int) */) {
                return divide(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, rhsValue_));
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active divide(double, double) */;
                        return divide(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active modulation(int, int) */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active modulation(double, double) */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active modulation(int, int) */;
            return modulation(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active modulation(double, double) */;
            return modulation(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active modulation(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return modulation(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active modulation(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return modulation(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectDouble(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult()));
            }
            if ((state & 0b10) != 0 /* is-active modulation(double, double) */) {
                return modulation(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectDouble(executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not modulation(int, int) && modulation(double, double) */ ? (Object) rhsValue_int : (Object) rhsValue_)));
        }

        @Override
        public int executeI32(VirtualFrame frameValue) throws UnexpectedResultException {
            int state = state_;
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return MJTypesGen.expectInteger(executeAndSpecialize(ex.getResult(), rhsValue));
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, ex.getResult()));
            }
            if ((state & 0b1) != 0 /* is-active modulation(int, int) */) {
                return modulation(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return MJTypesGen.expectInteger(executeAndSpecialize(lhsValue_, rhsValue_));
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active modulation(double, double) */;
                        return modulation(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b1110) == 0 /* only-active equal(int, int) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1101) == 0 /* only-active equal(char, char) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeGeneric_char_char1(frameValue, state);
            } else if ((state & 0b1011) == 0 /* only-active equal(double, double) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeGeneric_double_double2(frameValue, state);
            } else {
                return executeGeneric_generic3(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_char_char1(VirtualFrame frameValue, int state) {
            char lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            char rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active equal(char, char) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double2(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b10000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b1000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active equal(double, double) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic3(VirtualFrame frameValue, int state) {
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
            if ((state & 0b100) != 0 /* is-active equal(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_);
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
            if ((state & 0b1110) == 0 /* only-active equal(int, int) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeBool_int_int4(frameValue, state);
            } else if ((state & 0b1101) == 0 /* only-active equal(char, char) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeBool_char_char5(frameValue, state);
            } else if ((state & 0b1011) == 0 /* only-active equal(double, double) */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                return executeBool_double_double6(frameValue, state);
            } else {
                return executeBool_generic7(frameValue, state);
            }
        }

        private boolean executeBool_int_int4(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active equal(int, int) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_char_char5(VirtualFrame frameValue, int state) {
            char lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            char rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active equal(char, char) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double6(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b10000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b1000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not equal(int, int) && equal(char, char) && equal(double, double) && equal(Object, Object) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active equal(double, double) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic7(VirtualFrame frameValue, int state) {
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
            if ((state & 0b100) != 0 /* is-active equal(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 4) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 6) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b100 /* add-active equal(double, double) */;
                        return equal(lhsValue_, rhsValue_);
                    }
                }
            }
            this.state_ = state = state | 0b1000 /* add-active equal(Object, Object) */;
            return equal(lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b1111) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b1111) & ((state & 0b1111) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b1110) == 0 /* only-active notEqual(int, int) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1101) == 0 /* only-active equal(char, char) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeGeneric_char_char1(frameValue, state);
            } else if ((state & 0b1011) == 0 /* only-active notEqual(double, double) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeGeneric_double_double2(frameValue, state);
            } else {
                return executeGeneric_generic3(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active notEqual(int, int) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_char_char1(VirtualFrame frameValue, int state) {
            char lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            char rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active equal(char, char) */;
            return equal(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double2(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b10000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b1000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active notEqual(double, double) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic3(VirtualFrame frameValue, int state) {
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
            if ((state & 0b100) != 0 /* is-active notEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_);
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
            if ((state & 0b1110) == 0 /* only-active notEqual(int, int) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeBool_int_int4(frameValue, state);
            } else if ((state & 0b1101) == 0 /* only-active equal(char, char) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeBool_char_char5(frameValue, state);
            } else if ((state & 0b1011) == 0 /* only-active notEqual(double, double) */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                return executeBool_double_double6(frameValue, state);
            } else {
                return executeBool_generic7(frameValue, state);
            }
        }

        private boolean executeBool_int_int4(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active notEqual(int, int) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_char_char5(VirtualFrame frameValue, int state) {
            char lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            char rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeChar(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active equal(char, char) */;
            return equal(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double6(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b10000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b1000000) == 0 /* only-active 1:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b100000) == 0 /* only-active 0:double */ && (state & 0b1111) != 0  /* is-not notEqual(int, int) && equal(char, char) && notEqual(double, double) && notEqual(Object, Object) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b100) != 0 /* is-active notEqual(double, double) */;
            return notEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic7(VirtualFrame frameValue, int state) {
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
            if ((state & 0b100) != 0 /* is-active notEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b11000000) >>> 6 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 4) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 6) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b100 /* add-active notEqual(double, double) */;
                        return notEqual(lhsValue_, rhsValue_);
                    }
                }
            }
            this.state_ = state = state | 0b1000 /* add-active notEqual(Object, Object) */;
            return notEqual(lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b1111) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b1111) & ((state & 0b1111) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active less(int, int) */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active less(double, double) */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active less(int, int) */;
            return less(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active less(double, double) */;
            return less(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active less(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active less(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return less(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active less(int, int) */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeBool_int_int3(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active less(double, double) */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                return executeBool_double_double4(frameValue, state);
            } else {
                return executeBool_generic5(frameValue, state);
            }
        }

        private boolean executeBool_int_int3(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active less(int, int) */;
            return less(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double4(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not less(int, int) && less(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active less(double, double) */;
            return less(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic5(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active less(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return less(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active less(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active less(double, double) */;
                        return less(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active lessEqual(int, int) */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active lessEqual(double, double) */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active lessEqual(int, int) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active lessEqual(double, double) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active lessEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active lessEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active lessEqual(int, int) */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeBool_int_int3(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active lessEqual(double, double) */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                return executeBool_double_double4(frameValue, state);
            } else {
                return executeBool_generic5(frameValue, state);
            }
        }

        private boolean executeBool_int_int3(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active lessEqual(int, int) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double4(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not lessEqual(int, int) && lessEqual(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active lessEqual(double, double) */;
            return lessEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic5(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active lessEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return lessEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active lessEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active lessEqual(double, double) */;
                        return lessEqual(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active more(int, int) */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active more(double, double) */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active more(int, int) */;
            return more(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active more(double, double) */;
            return more(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active more(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active more(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return more(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active more(int, int) */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeBool_int_int3(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active more(double, double) */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                return executeBool_double_double4(frameValue, state);
            } else {
                return executeBool_generic5(frameValue, state);
            }
        }

        private boolean executeBool_int_int3(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active more(int, int) */;
            return more(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double4(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not more(int, int) && more(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active more(double, double) */;
            return more(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic5(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active more(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return more(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active more(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active more(double, double) */;
                        return more(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            if ((state & 0b10) == 0 /* only-active moreEqual(int, int) */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeGeneric_int_int0(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active moreEqual(double, double) */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeGeneric_double_double1(frameValue, state);
            } else {
                return executeGeneric_generic2(frameValue, state);
            }
        }

        private Object executeGeneric_int_int0(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active moreEqual(int, int) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_double_double1(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active moreEqual(double, double) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private Object executeGeneric_generic2(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active moreEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active moreEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            if ((state & 0b10) == 0 /* only-active moreEqual(int, int) */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeBool_int_int3(frameValue, state);
            } else if ((state & 0b1) == 0 /* only-active moreEqual(double, double) */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                return executeBool_double_double4(frameValue, state);
            } else {
                return executeBool_generic5(frameValue, state);
            }
        }

        private boolean executeBool_int_int3(VirtualFrame frameValue, int state) {
            int lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeI32(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            assert (state & 0b1) != 0 /* is-active moreEqual(int, int) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_double_double4(VirtualFrame frameValue, int state) {
            int lhsValue_int = 0;
            double lhsValue_;
            try {
                if ((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    lhsValue_int = this.lhs_.executeI32(frameValue);
                    lhsValue_ = MJTypes.castDouble(lhsValue_int);
                } else if ((state & 0b100) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    lhsValue_ = this.lhs_.executeDouble(frameValue);
                } else {
                    Object lhsValue__ = this.lhs_.executeGeneric(frameValue);
                    lhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            int rhsValue_int = 0;
            double rhsValue_;
            try {
                if ((state & 0b100000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    rhsValue_int = this.rhs_.executeI32(frameValue);
                    rhsValue_ = MJTypes.castDouble(rhsValue_int);
                } else if ((state & 0b10000) == 0 /* only-active 1:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */) {
                    rhsValue_ = this.rhs_.executeDouble(frameValue);
                } else {
                    Object rhsValue__ = this.rhs_.executeGeneric(frameValue);
                    rhsValue_ = MJTypesGen.expectImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue__);
                }
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(((state & 0b1000) == 0 /* only-active 0:double */ && (state & 0b11) != 0  /* is-not moreEqual(int, int) && moreEqual(double, double) */ ? (Object) lhsValue_int : (Object) lhsValue_), ex.getResult());
            }
            assert (state & 0b10) != 0 /* is-active moreEqual(double, double) */;
            return moreEqual(lhsValue_, rhsValue_);
        }

        private boolean executeBool_generic5(VirtualFrame frameValue, int state) {
            Object lhsValue_ = this.lhs_.executeGeneric(frameValue);
            Object rhsValue_ = this.rhs_.executeGeneric(frameValue);
            if ((state & 0b1) != 0 /* is-active moreEqual(int, int) */ && lhsValue_ instanceof Integer) {
                int lhsValue__ = (int) lhsValue_;
                if (rhsValue_ instanceof Integer) {
                    int rhsValue__ = (int) rhsValue_;
                    return moreEqual(lhsValue__, rhsValue__);
                }
            }
            if ((state & 0b10) != 0 /* is-active moreEqual(double, double) */ && MJTypesGen.isImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_)) {
                double lhsValue__ = MJTypesGen.asImplicitDouble((state & 0b1100) >>> 2 /* extract-implicit-active 0:double */, lhsValue_);
                if (MJTypesGen.isImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_)) {
                    double rhsValue__ = MJTypesGen.asImplicitDouble((state & 0b110000) >>> 4 /* extract-implicit-active 1:double */, rhsValue_);
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
            {
                int doubleCast0;
                if ((doubleCast0 = MJTypesGen.specializeImplicitDouble(lhsValue)) != 0) {
                    double lhsValue_ = MJTypesGen.asImplicitDouble(doubleCast0, lhsValue);
                    int doubleCast1;
                    if ((doubleCast1 = MJTypesGen.specializeImplicitDouble(rhsValue)) != 0) {
                        double rhsValue_ = MJTypesGen.asImplicitDouble(doubleCast1, rhsValue);
                        state = (state | (doubleCast0 << 2) /* set-implicit-active 0:double */);
                        state = (state | (doubleCast1 << 4) /* set-implicit-active 1:double */);
                        this.state_ = state = state | 0b10 /* add-active moreEqual(double, double) */;
                        return moreEqual(lhsValue_, rhsValue_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.lhs_, this.rhs_}, lhsValue, rhsValue);
        }

        @Override
        public NodeCost getCost() {
            int state = state_;
            if ((state & 0b11) == 0b0) {
                return NodeCost.UNINITIALIZED;
            } else if (((state & 0b11) & ((state & 0b11) - 1)) == 0 /* is-single-active  */) {
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
            boolean lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            boolean rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            boolean rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
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
            boolean lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            boolean rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
            if (state != 0 /* is-active equal(boolean, boolean) */) {
                return equal(lhsValue_, rhsValue_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            return executeAndSpecialize(lhsValue_, rhsValue_);
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            int state = state_;
            boolean lhsValue_;
            try {
                lhsValue_ = this.lhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                Object rhsValue = this.rhs_.executeGeneric(frameValue);
                return executeAndSpecialize(ex.getResult(), rhsValue);
            }
            boolean rhsValue_;
            try {
                rhsValue_ = this.rhs_.executeBool(frameValue);
            } catch (UnexpectedResultException ex) {
                return executeAndSpecialize(lhsValue_, ex.getResult());
            }
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
