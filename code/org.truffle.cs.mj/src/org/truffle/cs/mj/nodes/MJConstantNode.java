package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class MJConstantNode extends MJExpressionNode {


    public static abstract class IntNode extends MJConstantNode {
        private final int constant;

        public IntNode(int constant) {
            this.constant = constant;
        }

        @Specialization
        public int doInt() {
            return constant;
        }
    }

    public static abstract class DoubleNode extends MJConstantNode {
        private final double constant;


        public DoubleNode(double constant) {
            this.constant = constant;
        }

        @Specialization
        public double doDouble() {
            return constant;
        }
    }

    public static abstract class CharNode extends MJConstantNode {
        private final char constant;


        public CharNode(char constant) {
            this.constant = constant;
        }

        @Specialization
        public char doChar() {
            return constant;
        }
    }
}
