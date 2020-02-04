package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;

@NodeChild(value = "lhs", type = MJExpressionNode.class)
@NodeChild(value = "rhs", type = MJExpressionNode.class)
public abstract class MJBinaryNode extends MJExpressionNode {

    public static abstract class AddNode extends MJBinaryNode {
        @Specialization
        public int add(int lhs, int rhs) {
            return lhs + rhs;
        }
    }

    public static abstract class SubtractNode extends MJBinaryNode {
        @Specialization
        public int add(int lhs, int rhs) {
            return lhs - rhs;
        }
    }

    public static abstract class MultiplicationNode extends MJBinaryNode {
        @Specialization
        public int multiply(int lhs, int rhs) {
            return lhs * rhs;
        }
    }

    public static abstract class DividerNode extends MJBinaryNode {
        @Specialization
        public int multiply(int lhs, int rhs) {
            return lhs / rhs;
        }
    }

    public static abstract class ModulationNode extends MJBinaryNode {
        @Specialization
        public int multiply(int lhs, int rhs) {
            return lhs % rhs;
        }
    }

    public static abstract class EqualNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs == rhs;
        }

        @Specialization
        public boolean equal(Object lhs, Object rhs) {
            return lhs == rhs;
        }

    }

    public static abstract class NotEqualNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs != rhs;
        }

        @Specialization
        public boolean equal(Object lhs, Object rhs) {
            return lhs != rhs;
        }

    }

    public static abstract class LessNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs < rhs;
        }
    }

    public static abstract class LessEqualNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs <= rhs;
        }
    }

    public static abstract class GreaterNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs > rhs;
        }
    }

    public static abstract class GreaterEqualNode extends MJBinaryNode {
        @Specialization
        public boolean equal(int lhs, int rhs) {
            return lhs >= rhs;
        }
    }

    public static abstract class OrNode extends MJBinaryNode {
        @Specialization
        public boolean equal(boolean lhs, boolean rhs) {
            return lhs || rhs;
        }
    }

    public static abstract class AndNode extends MJBinaryNode {
        @Specialization
        public boolean equal(boolean lhs, boolean rhs) {
            return lhs && rhs;
        }
    }
}
