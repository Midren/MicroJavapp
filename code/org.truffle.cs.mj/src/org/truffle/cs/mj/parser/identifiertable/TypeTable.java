package org.truffle.cs.mj.parser.identifiertable;

import java.util.HashMap;
import java.util.Set;

import org.truffle.cs.mj.parser.identifiertable.types.TypeDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.BoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.CharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.DoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.IntDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantBoolDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantCharDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantDoubleDescriptor;
import org.truffle.cs.mj.parser.identifiertable.types.primitives.constants.ConstantIntDescriptor;

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
        typeDescriptors.put("bool", new BoolDescriptor().getInstance());
        typeDescriptors.put("int", new IntDescriptor().getInstance());
        typeDescriptors.put("char", new CharDescriptor().getInstance());
        typeDescriptors.put("double", new DoubleDescriptor().getInstance());
        typeDescriptors.put("const_bool", new ConstantBoolDescriptor().getInstance());
        typeDescriptors.put("const_int", new ConstantIntDescriptor().getInstance());
        typeDescriptors.put("const_char", new ConstantCharDescriptor().getInstance());
        typeDescriptors.put("const_double", new ConstantDoubleDescriptor().getInstance());
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
