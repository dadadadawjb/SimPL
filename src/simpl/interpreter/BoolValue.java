package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public String toString(State s) {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BoolValue)
            return b == ((BoolValue) other).b;
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof BoolValue)
            return b == ((BoolValue) other).b;
        else
            return false;
    }
}
