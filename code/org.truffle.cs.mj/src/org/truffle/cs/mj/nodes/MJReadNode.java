package org.truffle.cs.mj.nodes;

import java.io.IOException;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

public class MJReadNode extends MJStatementNode {
    String designator;
    FrameSlot frameSlot;
    TypeDescriptor typeDescriptor;

    public MJReadNode(String designator, FrameSlot frameSlot, TypeDescriptor typeDescriptor) {
        this.designator = designator;
        this.frameSlot = frameSlot;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        char c;
        try {
            c = (char) System.in.read();
        } catch (IOException e) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            e.printStackTrace();
            return null;
        }
        MJVariableNodeFactory.MJWriteLocalVariableNodeGen.create(MJConstantNodeFactory.CharNodeGen.create(c), frameSlot, typeDescriptor).execute(frame);
        return null;
    }

}
