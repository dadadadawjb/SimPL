package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class LessEq extends RelExpr {

    public LessEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <= " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value leftValue = l.eval(s);        // first left
        Value rightValue = r.eval(s);       // then right
        if (!(leftValue instanceof IntValue) || !(rightValue instanceof IntValue))
            throw new RuntimeError("LessEq requires two IntValue");             // actually never reach here depending on type checking
        if (((IntValue) leftValue).n <= ((IntValue) rightValue).n)
            return Value.TRUE;
        else
            return Value.FALSE;
    }

    @Override
    public LessEq substitute(Symbol x, Expr e) {
        // TODO
        // l[e/x] <= r[e/x]
        return new LessEq(l.substitute(x, e), r.substitute(x, e));
    }
}