package org.truffle.cs.mj.parser.identifiertable;

import java.util.HashMap;
import java.util.Set;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantBoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantCharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantDoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.constants.ConstantIntDescriptor;

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
        typeDescriptors.put("const_bool", ConstantBoolDescriptor.getInstance());
        typeDescriptors.put("const_int", ConstantIntDescriptor.getInstance());
        typeDescriptors.put("const_char", ConstantCharDescriptor.getInstance());
        typeDescriptors.put("const_double", ConstantDoubleDescriptor.getInstance());
    }

    public Set<String> getAvailableTypes() {
        return typeDescriptors.keySet();
    }

    public TypeDescriptor getTypeDescriptor(String identifier) {
        return typeDescriptors.get(identifier);
    }

    public TypeDescriptor getTypeDescriptor(String identifier, boolean isConst) {
        return typeDescriptors.get((isConst ? "const_" : "") + identifier);
    }

}
