package simpl.typing;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;

// the so-called Gamma
public abstract class TypeEnv {

    // map from variable to type (if polytype, instantiate first)
    public abstract Type get(Symbol x);

    // get all type variables already in the environment
    public abstract Set<TypeVar> allTypeVars();

    // overlap a new mapping into a type environment
    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x.equals(x1)) {
                    if (t instanceof PolyType)
                        return ((PolyType) t).instantiate();
                    else
                        return t;
                } else {
                    return E.get(x1);
                }
            }

            @Override
            public Set<TypeVar> allTypeVars() {
                Set<TypeVar> result = E.allTypeVars();
                result.addAll(t.allTypeVars());
                return result;
            }

            public String toString() {
                return x + ":" + t + ";" + E;
            }
        };
    }

    public static final TypeEnv empty = new TypeEnv() {
        @Override
        public Type get(Symbol x) {
            return null;
        }

        @Override
        public Set<TypeVar> allTypeVars() {
            return new HashSet<>();
        }
    };
}
