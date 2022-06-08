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

public class Continue extends Expr {

    public String toString() {
        return "continue";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return TypeResult.of(Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return Value.CONTINUE;
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // empty set
        return new HashSet<>();
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // empty set
        return new HashSet<>();
    }

    @Override
    public Continue substitute(Symbol x, Expr e) {
        // TODO
        // not substitute
        return this;
    }
}
