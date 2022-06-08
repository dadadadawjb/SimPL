package simpl.typing;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;
    // keep every type variable name unique
    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        // core unification algorithm
        if (t instanceof TypeVar) {
            if (this.contains((TypeVar) t))                 // the other also type variable and equal to this
                return Substitution.IDENTITY;
            else                                            // the other also type variable and not equal to this
                return Substitution.of(this, (TypeVar) t);
        } else {
            if (t.contains(this))                           // the other is not type variable and contains this
                throw new TypeCircularityError();
            else                                            // the other is not type variable and not contains this
                return Substitution.of(this, t);
        }
    }
    
    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return name.equals(tv.name);                        // contains means equal for type variable
    }
    
    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return (this.contains(a)) ? t : this;
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        // TODO
        Set<TypeVar> result = new HashSet<>();
        result.add(this);
        return result;
    }

    public String toString() {
        return "" + name;
    }
}
