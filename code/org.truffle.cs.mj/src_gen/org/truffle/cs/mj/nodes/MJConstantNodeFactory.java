// CheckStyle: start generated
package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import org.truffle.cs.mj.nodes.MJConstantNode;
import org.truffle.cs.mj.nodes.MJConstantNode.BoolNode;
import org.truffle.cs.mj.nodes.MJConstantNode.CharNode;
import org.truffle.cs.mj.nodes.MJConstantNode.DoubleNode;
import org.truffle.cs.mj.nodes.MJConstantNode.IntNode;

@GeneratedBy(MJConstantNode.class)
public final class MJConstantNodeFactory {

    @GeneratedBy(CharNode.class)
    public static final class CharNodeGen extends CharNode {

        private CharNodeGen(char constant) {
            super(constant);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return doChar();
        }

        @Override
        public char executeChar(VirtualFrame frameValue) {
            return doChar();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.MONOMORPHIC;
        }

        public static CharNode create(char constant) {
            return new CharNodeGen(constant);
        }

    }
    @GeneratedBy(IntNode.class)
    public static final class IntNodeGen extends IntNode {

        private IntNodeGen(int constant) {
            super(constant);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return doInt();
        }

        @Override
        public int executeI32(VirtualFrame frameValue) {
            return doInt();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.MONOMORPHIC;
        }

        public static IntNode create(int constant) {
            return new IntNodeGen(constant);
        }

    }
    @GeneratedBy(BoolNode.class)
    public static final class BoolNodeGen extends BoolNode {

        private BoolNodeGen(boolean constant) {
            super(constant);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return doBool();
        }

        @Override
        public boolean executeBool(VirtualFrame frameValue) {
            return doBool();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.MONOMORPHIC;
        }

        public static BoolNode create(boolean constant) {
            return new BoolNodeGen(constant);
        }

    }
    @GeneratedBy(DoubleNode.class)
    public static final class DoubleNodeGen extends DoubleNode {

        private DoubleNodeGen(double constant) {
            super(constant);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return doDouble();
        }

        @Override
        public double executeDouble(VirtualFrame frameValue) {
            return doDouble();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.MONOMORPHIC;
        }

        public static DoubleNode create(double constant) {
            return new DoubleNodeGen(constant);
        }

    }
}
