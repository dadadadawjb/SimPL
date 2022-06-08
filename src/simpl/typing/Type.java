package simpl.typing;

import java.util.Set;

public abstract class Type {

    // Judge whether this type is an equality type
    public abstract boolean isEqualityType();

    // Unification algorithm steps, this type equal to another type and return the substitution
    public abstract Substitution unify(Type t) throws TypeError;
    
    // Judge whether another type variable is contained in this type
    public abstract boolean contains(TypeVar tv);

    // Replace a type variable contained in this type with another type and return back the new type without modifying this type
    public abstract Type replace(TypeVar a, Type t);

    // Return all type variables contained in this type
    public abstract Set<TypeVar> allTypeVars();

    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();
}
