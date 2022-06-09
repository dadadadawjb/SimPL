package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value leftValue = l.eval(s);        // first left
        Value rightValue = r.eval(s);       // then right
        if (!(leftValue instanceof IntValue) || !(rightValue instanceof IntValue))
            throw new RuntimeError("Add requires two IntValue");        // actually never reach here depending on type checking
        return new IntValue(((IntValue) leftValue).n + ((IntValue) rightValue).n);
    }

    @Override
    public Add substitute(Symbol x, Expr e) {
        // l[e/x] + r[e/x]
        return new Add(l.substitute(x, e), r.substitute(x, e));
    }
}
