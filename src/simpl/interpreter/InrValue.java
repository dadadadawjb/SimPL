package simpl.interpreter;

public class InrValue extends Value {

    public final Value v;

    public InrValue(Value v) {
        this.v = v;
    }

    public String toString() {
        return "inr@" + v;
    }

    @Override
    public String toString(State s) {
        return "inr@" + v.toString(s);
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof InrValue)
            return v.equals(((InrValue) other).v);
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        if (other instanceof InrValue)
            return v.equals(((InrValue) other).v);
        else
            return false;
    }
}
