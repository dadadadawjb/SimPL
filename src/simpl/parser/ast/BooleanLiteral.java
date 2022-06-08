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

public class BooleanLiteral extends Expr {

    public boolean b;

    public BooleanLiteral(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return TypeResult.of(Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return (b) ? Value.TRUE : Value.FALSE;
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
    public BooleanLiteral substitute(Symbol x, Expr e) {
        // TODO
        // not substitute
        return this;
    }
}
