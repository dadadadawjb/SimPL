package simpl.typing;

import java.util.Set;
import java.util.HashSet;

// universal polymorphic type
// currently only used in let-polymorphism and predefined generic templates
public class PolyType extends Type {
    // not understand completely

    Set<TypeVar> universalTypeVars;
    Type originalType;

    // all type variables are universal, always used in creating an universal type composing of all universal polymorphic type variables
    public PolyType(Type t) {
        universalTypeVars = t.allTypeVars();
        originalType = t;
    }
    // some type variables may be concrete, some type variables may contain more type variables
    public PolyType(Type t, Substitution s) {
        universalTypeVars = new HashSet<>();
        Set<TypeVar> temp = t.allTypeVars();
        for (TypeVar tv : temp)
            universalTypeVars.addAll(s.apply(tv).allTypeVars());
        originalType = t;
    }
    // all type variables are universal, but some type variables may be bound
    public PolyType(Type t, TypeEnv E) {
        universalTypeVars = t.allTypeVars();
        universalTypeVars.removeAll(E.allTypeVars());
        originalType = t;
    }
    // some type variables may be concrete, some type variables may contain more type variables, but some type variables may be bound
    public PolyType(Type t, Substitution s, TypeEnv E) {
        universalTypeVars = new HashSet<>();
        Set<TypeVar> temp = t.allTypeVars();
        for (TypeVar tv : temp)
            universalTypeVars.addAll(s.apply(tv).allTypeVars());
        universalTypeVars.removeAll(E.allTypeVars());
        originalType = t;
    }
    
    @Override
    public boolean isEqualityType() {
        // TODO
        return originalType.isEqualityType();           // actually in now situation never used
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        return Substitution.IDENTITY;                   // actually never reach here
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return originalType.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return this;                                    // actually never reach here
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        // TODO
        return new HashSet<>();
    }

    // every time encounter this universal type, instantiate it with a new set of type variables
    public Type instantiate() {
        Substitution s = Substitution.IDENTITY;
        for (TypeVar t : universalTypeVars)
            s = s.compose(Substitution.of(t, new TypeVar(t.isEqualityType())));
        return s.apply(originalType);
    }

    public String toString() {
        return "" + originalType;
    }
}
