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
        // IMPORTANT
        return TypeResult.of(Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        return new IntValue(n);
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
    public IntegerLiteral substitute(Symbol x, Expr e) {
        // not substitute
        return this;
    }
}
