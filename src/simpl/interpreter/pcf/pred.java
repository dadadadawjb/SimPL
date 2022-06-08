package simpl.interpreter.pcf;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class pred extends FunValue {

    public pred() {
        super(Env.empty, Symbol.symbol("x"), getBody());    // x does not matter, since x is only used in Env.empty[x->v]
    }

    private static Expr getBody() {
        // TODO
        // x - 1
        Expr body = new Expr() {
            public String toString() {
                return "pred";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                // already in default type environment
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                Value value = s.E.get(Symbol.symbol("x"));
                if (!(value instanceof IntValue))
                    throw new RuntimeError("pred requires IntValue");                   // actually never reach here depending on type checking
                return new IntValue(((IntValue) value).n - 1);
            }

            @Override
            public Set<Symbol> FV() {
                // {x}
                Set<Symbol> result = new HashSet<>();
                result.add(Symbol.symbol("x"));
                return result;
            }

            @Override
            public Set<Symbol> Vars() {
                // {x}
                Set<Symbol> result = new HashSet<>();
                result.add(Symbol.symbol("x"));
                return result;
            }

            @Override
            public Expr substitute(Symbol x, Expr e) {
                // not substitute
                return this;
            }
        };
        return body;
    }
}
