package simpl.interpreter;

public class InlValue extends Value {

    public final Value v;

    public InlValue(Value v) {
        this.v = v;
    }

    public String toString() {
        return "inl@" + v;
    }

    @Override
    public String toString(State s) {
        return "inl@" + v.toString(s);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof InlValue)
            return v.equals(((InlValue) other).v);
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof InlValue)
            return v.equals(((InlValue) other).v);
        else
            return false;
    }
}
