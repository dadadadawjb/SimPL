package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public FunValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public String toString(State s) {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        return false;       // not equality type
    }

    @Override
    public boolean equal(Object other) {
        return false;       // not correct, but never used to judge function shape comparison
    }
}
