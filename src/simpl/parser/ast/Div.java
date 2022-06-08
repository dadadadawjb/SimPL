package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value leftValue = l.eval(s);        // first left
        Value rightValue = r.eval(s);       // then right
        if (!(leftValue instanceof IntValue) || !(rightValue instanceof IntValue))
            throw new RuntimeError("Div requires two IntValue");        // actually never reach here depending on type checking
        if (((IntValue) rightValue).n == 0)
            throw new RuntimeError("Div by zero");
        return new IntValue(((IntValue) leftValue).n / ((IntValue) rightValue).n);
    }

    @Override
    public Div substitute(Symbol x, Expr e) {
        // TODO
        // l[e/x] / r[e/x]
        return new Div(l.substitute(x, e), r.substitute(x, e));
    }
}
