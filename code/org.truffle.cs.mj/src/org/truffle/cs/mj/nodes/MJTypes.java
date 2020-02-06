package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeSystem;

@TypeSystem({char.class, boolean.class, double.class, int.class})
public abstract class MJTypes {
    @ImplicitCast
    public static double castDouble(int value) {
        return value;
    }
}
