package simpl.typing;

import java.util.Set;
import java.util.HashSet;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof BoolType)
            return Substitution.IDENTITY;
        else if (t instanceof TypeVar)
            return t.unify(this);
        else
            throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return Type.BOOL;
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        // TODO
        Set<TypeVar> result = new HashSet<>();
        return result;
    }

    public String toString() {
        return "bool";
    }
}
