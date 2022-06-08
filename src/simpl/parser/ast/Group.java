package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Group extends UnaryExpr {

    public Group(Expr e) {
        super(e);
    }

    public String toString() {
        return "" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return e.typecheck(E);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return e.eval(s);
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // FV(e)
        return e.FV();
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // Vars(e)
        return e.Vars();
    }

    @Override
    public Group substitute(Symbol x, Expr e) {
        // TODO
        // (e'[e/x])
        return new Group(this.e.substitute(x, e));
    }
}
