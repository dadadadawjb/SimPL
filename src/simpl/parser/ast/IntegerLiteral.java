package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class IntegerLiteral extends Expr {

    public int n;

    public IntegerLiteral(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return TypeResult.of(Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new IntValue(n);
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
    public IntegerLiteral substitute(Symbol x, Expr e) {
        // TODO
        // not substitute
        return this;
    }
}
