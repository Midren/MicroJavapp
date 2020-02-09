package org.truffle.cs.mj.nodes;

import java.io.IOException;
import java.util.Scanner;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

public class MJReadNode extends MJExpressionNode {

    @TruffleBoundary
    private static char scanChar() {
        return new Scanner(System.in).next().charAt(0);
    }

    @Override
    public TypeDescriptor getType() {
        return new CharDescriptor().getInstance();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return scanChar();
    }

}
