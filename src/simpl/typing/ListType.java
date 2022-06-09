package simpl.typing;

import java.util.Set;
import java.util.HashSet;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // IMPORTANT
        if (t instanceof ListType)
            return this.t.unify(((ListType) t).t);
        else if (t instanceof TypeVar)
            return t.unify(this);
        else
            throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new ListType(this.t.replace(a, t));
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        Set<TypeVar> result = new HashSet<>(t.allTypeVars());
        return result;
    }

    public String toString() {
        return t + " list";
    }
}
