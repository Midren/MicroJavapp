package org.truffle.cs.mj.nodes;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class MJConstantNode extends MJExpressionNode {

    public static abstract class CharNode extends MJConstantNode {
        private final char constant;

        @Override
        public TypeDescriptor getType() {
            return CharDescriptor.getInstance();
        }

        public CharNode(char constant) {
            this.constant = constant;
        }

        @Specialization
        public char doChar() {
            return constant;
        }
    }

    public static abstract class IntNode extends MJConstantNode {
        private final int constant;

        @Override
        public TypeDescriptor getType() {
            return IntDescriptor.getInstance();
        }

        public IntNode(int constant) {
            this.constant = constant;
        }

        @Specialization
        public int doInt() {
            return constant;
        }
    }

    public static abstract class BoolNode extends MJConstantNode {
        private final boolean constant;

        @Override
        public TypeDescriptor getType() {
            return BoolDescriptor.getInstance();
        }

        public BoolNode(boolean constant) {
            this.constant = constant;
        }

        @Specialization
        public boolean doBool() {
            return constant;
        }
    }

    public static abstract class DoubleNode extends MJConstantNode {
        private final double constant;

        @Override
        public TypeDescriptor getType() {
            return DoubleDescriptor.getInstance();
        }

        public DoubleNode(double constant) {
            this.constant = constant;
        }

        @Specialization
        public double doDouble() {
            return constant;
        }
    }
}
