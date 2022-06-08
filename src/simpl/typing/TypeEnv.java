package simpl.typing;

import simpl.parser.Symbol;

// the so-called Gamma
public abstract class TypeEnv {

    // map from variable to type (if polytype, instantiate first)
    public abstract Type get(Symbol x);

    // overlap a new mapping into a type environment
    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x.equals(x1)) {
                    return t;
                } else {
                    return E.get(x1);
                }
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
    };
}
