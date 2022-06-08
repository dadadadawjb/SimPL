package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class RecValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public RecValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "rec";           // actually never reach here since never evaluate to a raw rec value
    }

    @Override
    public String toString(State s) {
        return "rec";           // actually never reach here since never evaluate to a raw rec value
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return false;       // not equality type
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        return false;       // not correct, but never used to judge recursion shape comparison
    }
}
