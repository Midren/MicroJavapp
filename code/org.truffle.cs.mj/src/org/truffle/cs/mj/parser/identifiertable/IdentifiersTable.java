package org.truffle.cs.mj.parser.identifiertable;

import java.util.HashMap;
import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class IdentifiersTable {
    private HashMap<String, TypeDescriptor> identifiersMap;

    HashMap<String, TypeDescriptor> typeDescriptors;

    private FrameDescriptor frameDescriptor;

    public IdentifiersTable() {
        identifiersMap = new HashMap<>();
        typeDescriptors = new HashMap<>();
        frameDescriptor = new FrameDescriptor();
        addBuiltinTypes();
    }

    private void addBuiltinTypes() {
        typeDescriptors.put("bool", BoolDescriptor.getInstance());
        typeDescriptors.put("int", IntDescriptor.getInstance());
        typeDescriptors.put("char", CharDescriptor.getInstance());
        typeDescriptors.put("double", DoubleDescriptor.getInstance());
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

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return typeDescriptors.get(identifier);
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
