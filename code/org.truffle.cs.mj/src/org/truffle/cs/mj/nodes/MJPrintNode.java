package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;

@NodeChild(value = "expression", type = MJExpressionNode.class)
public abstract class MJPrintNode extends MJStatementNode {
    @Child MJExpressionNode param;

    @Specialization
    Object printI(int i) {
        print(i);
        return null;
    }

    @Specialization
    Object printI(char c) {
        print(c);
        return null;
    }

    @Specialization
    Object printO(Object o) {
        print(o);
        return null;
    }

    @TruffleBoundary
    private static void print(int i) {
        System.out.println(i);
    }

    @TruffleBoundary
    private static void print(char c) {
        System.out.println(c);
    }

    @TruffleBoundary
    private static void print(Object o) {
        System.out.print(o);
    }
}
