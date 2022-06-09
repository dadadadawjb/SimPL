package simpl.interpreter;

class NilValue extends Value {

    protected NilValue() {
    }

    public String toString() {
        return "nil";
    }

    @Override
    public String toString(State s) {
        return "nil";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof NilValue)
            return true;
        else
            return false;
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof NilValue)
            return true;
        else
            return false;
    }
}
