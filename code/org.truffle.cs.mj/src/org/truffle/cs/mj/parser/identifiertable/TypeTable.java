package org.truffle.cs.mj.parser.identifiertable;

import java.util.HashMap;
import java.util.Set;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;

public class TypeTable {

    HashMap<String, TypeDescriptor> typeDescriptors;

    private static TypeTable instance = new TypeTable();

    private TypeTable() {
        typeDescriptors = new HashMap<>();
        addBuiltinTypes();
    }

    public static TypeTable getInstance() {
        return instance;
    }

    private void addBuiltinTypes() {
        typeDescriptors.put("bool", BoolDescriptor.getInstance());
        typeDescriptors.put("int", IntDescriptor.getInstance());
        typeDescriptors.put("char", CharDescriptor.getInstance());
        typeDescriptors.put("double", DoubleDescriptor.getInstance());
    }

    public Set<String> getAvailableTypes() {
        return typeDescriptors.keySet();
    }

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return typeDescriptors.get(identifier);
    }

}
