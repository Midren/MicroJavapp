// CheckStyle: start generated
package org.truffle.cs.mj.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.truffle.cs.mj.nodes.MJTypes;

@GeneratedBy(MJTypes.class)
public final class MJTypesGen extends MJTypes {

    @Deprecated public static final MJTypesGen M_J_TYPES = new MJTypesGen();

    protected MJTypesGen() {
    }

    public static boolean isCharacter(Object value) {
        return value instanceof Character;
    }

    public static char asCharacter(Object value) {
        assert value instanceof Character : "MJTypesGen.asCharacter: char expected";
        return (char) value;
    }

    public static char expectCharacter(Object value) throws UnexpectedResultException {
        if (value instanceof Character) {
            return (char) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isBoolean(Object value) {
        return value instanceof Boolean;
    }

    public static boolean asBoolean(Object value) {
        assert value instanceof Boolean : "MJTypesGen.asBoolean: boolean expected";
        return (boolean) value;
    }

    public static boolean expectBoolean(Object value) throws UnexpectedResultException {
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isDouble(Object value) {
        return value instanceof Double;
    }

    public static double asDouble(Object value) {
        assert value instanceof Double : "MJTypesGen.asDouble: double expected";
        return (double) value;
    }

    public static double expectDouble(Object value) throws UnexpectedResultException {
        if (value instanceof Double) {
            return (double) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isInteger(Object value) {
        return value instanceof Integer;
    }

    public static int asInteger(Object value) {
        assert value instanceof Integer : "MJTypesGen.asInteger: int expected";
        return (int) value;
    }

    public static int expectInteger(Object value) throws UnexpectedResultException {
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static double expectImplicitDouble(int state, Object value) throws UnexpectedResultException {
        if ((state & 0b1) != 0 && value instanceof Integer) {
            return castDouble((int) value);
        } else if ((state & 0b10) != 0 && value instanceof Double) {
            return (double) value;
        } else {
            throw new UnexpectedResultException(value);
        }
    }

    public static boolean isImplicitDouble(int state, Object value) {
        return ((state & 0b1) != 0 && value instanceof Integer)
             || ((state & 0b10) != 0 && value instanceof Double);
    }

    public static boolean isImplicitDouble(Object value) {
        return value instanceof Integer
             || value instanceof Double;
    }

    public static double asImplicitDouble(int state, Object value) {
        if ((state & 0b1) != 0 && value instanceof Integer) {
            return castDouble((int) value);
        } else if ((state & 0b10) != 0 && value instanceof Double) {
            return (double) value;
        } else {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new IllegalArgumentException("Illegal implicit source type.");
        }
    }

    public static double asImplicitDouble(Object value) {
        if (value instanceof Integer) {
            return castDouble((int) value);
        } else if (value instanceof Double) {
            return (double) value;
        } else {
            throw new IllegalArgumentException("Illegal implicit source type.");
        }
    }

    public static int specializeImplicitDouble(Object value) {
        if (value instanceof Integer) {
            return 0b1;
        } else if (value instanceof Double) {
            return 0b10;
        } else {
            return 0;
        }
    }

}
