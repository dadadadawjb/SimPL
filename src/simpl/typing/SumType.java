package simpl.typing;

import java.util.Set;
import java.util.HashSet;

public final class SumType extends Type {

    // t1 + t2
    public Type t1, t2;

    public SumType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        return t1.isEqualityType() && t2.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // IMPORTANT
        if (t instanceof SumType) {
            Substitution substitution = t1.unify(((SumType) t).t1);
            Type t2_ = substitution.apply(t2);
            Type t2_prime_ = substitution.apply(((SumType) t).t2);
            substitution = substitution.compose(t2_.unify(t2_prime_));
            return substitution;
        } else if (t instanceof TypeVar) {
            return t.unify(this);
        } else {
            throw new TypeMismatchError();
        }
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new SumType(t1.replace(a, t), t2.replace(a, t));
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        Set<TypeVar> result = new HashSet<>(t1.allTypeVars());
        result.addAll(t2.allTypeVars());
        return result;
    }

    public String toString() {
        return "(" + t1 + " + " + t2 + ")";
    }
}
