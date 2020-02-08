package org.truffle.cs.mj.parser.identifiertable;

import java.util.HashMap;
import java.util.Set;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class IdentifiersTable {
    private HashMap<String, TypeDescriptor> identifiersMap;

    private FrameDescriptor frameDescriptor;

    public IdentifiersTable() {
        identifiersMap = new HashMap<>();
        frameDescriptor = new FrameDescriptor();
    }

    public FrameSlot getFrameSlot(String identifier) {
        return frameDescriptor.findFrameSlot(identifier);
    }

    public FrameDescriptor getFrameDescriptor() {
        return frameDescriptor;
    }

    public TypeDescriptor getIdentifierDescriptor(String identifier) {
        return identifiersMap.get(identifier);
    }

    public void addVariable(String identifier, TypeDescriptor descriptor) {
        addNewIdentifier(identifier, descriptor);
    }

    private FrameSlot addNewIdentifier(String identifier, TypeDescriptor typeDescriptor) {
        if (identifiersMap.containsKey(identifier)) {
            throw new Error("Double declaration");
        }
        identifiersMap.put(identifier, typeDescriptor);
        return frameDescriptor.addFrameSlot(identifier, typeDescriptor.getSlotKind());
    }
}
