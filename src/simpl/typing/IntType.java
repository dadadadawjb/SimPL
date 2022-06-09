package simpl.typing;

import java.util.Set;
import java.util.HashSet;

final class IntType extends Type {

    protected IntType() {
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // IMPORTANT
        if (t instanceof IntType)
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
        return Type.INT;
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        Set<TypeVar> result = new HashSet<>();
        return result;
    }

    public String toString() {
        return "int";
    }
}
