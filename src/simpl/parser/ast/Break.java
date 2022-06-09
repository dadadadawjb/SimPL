package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Break extends Expr {

    public String toString() {
        return "break";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        return TypeResult.of(Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        return Value.BREAK;
    }

    @Override
    public Set<Symbol> FV() {
        // empty set
        return new HashSet<>();
    }

    @Override
    public Set<Symbol> Vars() {
        // empty set
        return new HashSet<>();
    }

    @Override
    public Break substitute(Symbol x, Expr e) {
        // not substitute
        return this;
    }
}
