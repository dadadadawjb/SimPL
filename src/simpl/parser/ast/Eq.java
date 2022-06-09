package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Eq extends EqExpr {

    public Eq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " = " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value leftValue = l.eval(s);        // first left
        Value rightValue = r.eval(s);       // then right
        if (leftValue.equals(rightValue))
            return Value.TRUE;
        else
            return Value.FALSE;
    }

    @Override
    public Eq substitute(Symbol x, Expr e) {
        // l[e/x] = r[e/x]
        return new Eq(l.substitute(x, e), r.substitute(x, e));
    }
}
