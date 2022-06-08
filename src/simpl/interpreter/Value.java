package simpl.interpreter;

public abstract class Value {

    public static final Value NIL = new NilValue();
    public static final Value UNIT = new UnitValue();
    public static final Value BREAK = new BreakValue();
    public static final Value CONTINUE = new ContinueValue();
    public static final Value TRUE = new BoolValue(true);
    public static final Value FALSE = new BoolValue(false);

    public abstract boolean equals(Object other);           // used for equality comparison
    public abstract boolean equal(Object other);            // used for value shape comparison

    public abstract String toString(State s);
}
