package simpl.interpreter;

class UnitValue extends Value {

    protected UnitValue() {
    }

    public String toString() {
        return "unit";
    }

    @Override
    public String toString(State s) {
        return "unit";
    }

    @Override
    public boolean equals(Object other) {
        return false;       // not equality type
    }

    @Override
    public boolean equal(Object other) {
        if (other instanceof UnitValue)
            return true;
        else
            return false;
    }
}
