package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public String toString(State s) {
        return "pair@" + v1.toString(s) + "@" + v2.toString(s);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PairValue) {
            PairValue otherPairValue = (PairValue) other;
            return v1.equals(otherPairValue.v1) && v2.equals(otherPairValue.v2);
        } else {
            return false;
        }
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof PairValue) {
            PairValue otherPairValue = (PairValue) other;
            return v1.equal(otherPairValue.v1) && v2.equal(otherPairValue.v2);
        } else {
            return false;
        }
    }
}
