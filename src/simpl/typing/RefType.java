package simpl.typing;

import java.util.Set;
import java.util.HashSet;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return true;        // not care about the type of the reference
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // IMPORTANT
        if (t instanceof RefType)
            return this.t.unify(((RefType) t).t);
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
        return new RefType(this.t.replace(a, t));
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        Set<TypeVar> result = new HashSet<>(t.allTypeVars());
        return result;
    }

    public String toString() {
        return t + " ref";
    }
}
