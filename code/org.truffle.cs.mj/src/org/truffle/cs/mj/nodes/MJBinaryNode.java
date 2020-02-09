package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

@NodeChild(value = "lhs", type = MJExpressionNode.class)
@NodeChild(value = "rhs", type = MJExpressionNode.class)
public abstract class MJBinaryNode extends MJExpressionNode {

    public abstract MJExpressionNode getLhs();

    public static abstract class AddNode extends MJBinaryNode {

        @Override
        public TypeDescriptor getType() {
            return getLhs().getType();
        }

        @Specialization
        public int add(int lhs, int rhs) {
            return lhs + rhs;
        }

        @Specialization
        public double add(double lhs, double rhs) {
            return lhs + rhs;
        }
    }

    public static abstract class SubtractNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return getLhs().getType();
        }

        @Specialization
        public int subtract(int lhs, int rhs) {
            return lhs - rhs;
        }

        @Specialization
        public double subtract(double lhs, double rhs) {
            return lhs - rhs;
        }
    }

    public static abstract class MultiplicationNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return getLhs().getType();
        }

        @Specialization
        public int multiply(int lhs, int rhs) {
            return lhs * rhs;
        }

        @Specialization
        public double multiply(double lhs, double rhs) {
            return lhs * rhs;
        }
    }

    public static abstract class DividerNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return getLhs().getType();
        }

        @Specialization
        public int divide(int lhs, int rhs) {
            return lhs / rhs;
        }

        @Specialization
        public double divide(double lhs, double rhs) {
            return lhs / rhs;
        }
    }

    public static abstract class ModulationNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return getLhs().getType();
        }

        @Specialization
        public int modulation(int lhs, int rhs) {
            return lhs % rhs;
        }

        @Specialization
        public double modulation(double lhs, double rhs) {
            return lhs % rhs;
        }
    }

    public static abstract class EqualNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs == rhs;
        }

        @Specialization
        public boolean equal(char lhs, char rhs) {
            return lhs == rhs;
        }

        @Specialization
        public boolean equal(double lhs, double rhs) {
            return lhs == rhs;
        }

        @Specialization
        public boolean equal(Object lhs, Object rhs) {
            return lhs == rhs;
        }

    }

    public static abstract class NotEqualNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean notEqual(int lhs, int rhs) {
            return lhs != rhs;
        }

        @Specialization
        public boolean equal(char lhs, char rhs) {
            return lhs != rhs;
        }

        @Specialization
        public boolean notEqual(double lhs, double rhs) {
            return lhs != rhs;
        }

        @Specialization
        public boolean notEqual(Object lhs, Object rhs) {
            return lhs != rhs;
        }

    }

    public static abstract class LessNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean less(int lhs, int rhs) {
            return lhs < rhs;
        }

        @Specialization
        public boolean less(double lhs, double rhs) {
            return lhs < rhs;
        }
    }

    public static abstract class LessEqualNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean lessEqual(int lhs, int rhs) {
            return lhs <= rhs;
        }

        @Specialization
        public boolean lessEqual(double lhs, double rhs) {
            return lhs <= rhs;
        }
    }

    public static abstract class GreaterNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean more(int lhs, int rhs) {
            return lhs > rhs;
        }

        @Specialization
        public boolean more(double lhs, double rhs) {
            return lhs > rhs;
        }
    }

    public static abstract class GreaterEqualNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean moreEqual(int lhs, int rhs) {
            return lhs >= rhs;
        }

        @Specialization
        public boolean moreEqual(double lhs, double rhs) {
            return lhs >= rhs;
        }
    }

    public static abstract class OrNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean equal(boolean lhs, boolean rhs) {
            return lhs || rhs;
        }
    }

    public static abstract class AndNode extends MJBinaryNode {
        @Override
        public TypeDescriptor getType() {
            return new BoolDescriptor().getInstance();
        }

        @Specialization
        public boolean equal(boolean lhs, boolean rhs) {
            return lhs && rhs;
        }
    }
}
