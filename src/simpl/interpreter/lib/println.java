package simpl.interpreter.lib;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class println extends FunValue {

    public println() {
        super(Env.empty, Symbol.symbol("x"), getBody());    // x does not matter, since x is only used in Env.empty[x->v]
    }

    private static Expr getBody() {
        // TODO
        // println(x)
        Expr body = new Expr() {
            public String toString() {
                return "println x";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                // already in default type environment
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                Value value = s.E.get(Symbol.symbol("x"));
                System.out.println(value.toString(s));
                return Value.UNIT;
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
