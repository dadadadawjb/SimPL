package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;          // follow by the memory position rather than the content
    }

    @Override
    public String toString(State s) {
        return "ref@" + s.M.get(p).toString(s);
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof RefValue)
            return p == ((RefValue) other).p;
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        if (other instanceof RefValue)
            return p == ((RefValue) other).p;
        else
            return false;
    }
}
