package simpl.typing;

import java.util.Set;
import java.util.HashSet;

final class UnitType extends Type {

    protected UnitType() {
    }

    @Override
    public boolean isEqualityType() {
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // IMPORTANT
        if (t instanceof UnitType)
            return Substitution.IDENTITY;
        else if (t instanceof TypeVar)
            return t.unify(this);
        else
            throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return Type.UNIT;
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        Set<TypeVar> result = new HashSet<>();
        return result;
    }

    public String toString() {
        return "unit";
    }
}
