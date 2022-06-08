package simpl.interpreter;

class BreakValue extends Value {

    protected BreakValue() {
    }

    public String toString() {
        return "break";
    }

    @Override
    public String toString(State s) {
        return "break";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return false;       // not equality type
    }

    @Override
    public boolean equal(Object other) {
        // TODO
        if (other instanceof BreakValue)
            return true;
        else
            return false;
    }
}
