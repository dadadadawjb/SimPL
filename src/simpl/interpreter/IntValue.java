package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public String toString(State s) {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof IntValue)
            return n == ((IntValue) other).n;
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof IntValue)
            return n == ((IntValue) other).n;
        else
            return false;
    }
}
