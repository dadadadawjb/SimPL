package simpl.typing;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;

// the substitution / solution
public abstract class Substitution {

    // apply the substitution to a type
    public abstract Type apply(Type t);

    private static final class Identity extends Substitution {
        // x -> x
        public Type apply(Type t) {
            return t;
        }
    }

    private static final class Replace extends Substitution {
        // a -> t
        private TypeVar a;
        private Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }

        public Type apply(Type b) {
            return (b == null) ? null : b.replace(a, t);
        }
    }

    private static final class Compose extends Substitution {
        // (f . g)(x) = f(g(x))
        private Substitution f, g;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
        }

        public Type apply(Type t) {
            return f.apply(g.apply(t));
        }
    }

    public static final Substitution IDENTITY = new Identity();

    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }

    public Substitution compose(Substitution inner) {
        return new Compose(inner, this);                // make the caller first and then the callee
    }

    // compose the type environment with the substitution, i.e. q[s/a]
    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            @Override
            public Type get(Symbol x) {
                Type type = E.get(x);
                return apply(type);
            }

            @Override
			public Set<TypeVar> allTypeVars() {
				Set<TypeVar> result = new HashSet<>();
				for (TypeVar tv : E.allTypeVars()) {
					Type tvNew = apply(tv);
					result.addAll(tvNew.allTypeVars());
				}
				return result;
			}
        };
    }
}
