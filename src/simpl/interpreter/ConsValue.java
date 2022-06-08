package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;
    public final int length;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
        if (v2 == Value.NIL)
            this.length = 1;
        else
            this.length = ((ConsValue) v2).length + 1;      // depend on the type checking of v2 as ConsValue before new
    }

    public String toString() {
        return "list@" + this.length;
    }

    @Override
    public String toString(State s) {
        return "list@" + this.length;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof ConsValue) {
            ConsValue otherConsValue = (ConsValue) other;
            return v1.equals(otherConsValue.v1) && v2.equals(otherConsValue.v2);
        } else {
            return false;
        }
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        if (other instanceof ConsValue) {
            ConsValue otherConsValue = (ConsValue) other;
            return v1.equal(otherConsValue.v1) && v2.equal(otherConsValue.v2);
        } else {
            return false;
        }
    }
}
