package simpl.interpreter;

import java.util.Map;
import java.util.HashMap;
import simpl.parser.Symbol;

// mapping from variable names to values
public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        if (y.equals(x))
            return v;
        else
            return E.get(y);
    }

    public Env clone() {
        return new Env(E.clone(), x, v);
    }

    public Map<Symbol, RefValue> allReferences() {
        Map<Symbol, RefValue> map = new HashMap<>();
        Symbol symbol = x;
        Value value = v;
        Env env = E;
        while (true) {
            if (value instanceof RefValue && !map.containsKey(symbol))
                map.put(symbol, (RefValue) value);
            
            if (env != null) {
                symbol = env.x;
                value = env.v;
                env = env.E;
            } else {
                break;
            }
        }
        return map;
    }
}
