package simpl.interpreter;

class ContinueValue extends Value {

    protected ContinueValue() {
    }

    public String toString() {
        return "continue";
    }

    @Override
    public String toString(State s) {
        return "continue";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return false;       // not equality type
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        if (other instanceof ContinueValue)
            return true;
        else
            return false;
    }
}
