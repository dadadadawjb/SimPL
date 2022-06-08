package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult1 = e1.typecheck(E);                               // first check `e1`
        TypeEnv newE = typeResult1.s.compose(E);                                // update the new type environment for solving the rest type constraints
        TypeResult typeResult2 = e2.typecheck(newE);                            // then check `e2`

        Substitution substitution = typeResult1.s.compose(typeResult2.s);       // first solve left `q1` then right `q2`
        Type type1 = substitution.apply(typeResult1.t);                         // do substitution
        if (type1 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type1.unify(Type.BOOL));            // then solve the type constraint `t1 = bool`
        Type type2 = substitution.apply(typeResult2.t);                         // do substitution
        if (type2 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type2.unify(Type.UNIT));            // then solve the type constraint `t2 = unit`

        return TypeResult.of(substitution, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value1 = e1.eval(s);                  // first the condition
        if (!(value1 instanceof BoolValue))
            throw new RuntimeError("Loop requires BoolValue");          // actually never reach here depending on type checking
        if (((BoolValue) value1).equal(Value.TRUE)) {
            Expr newExpr = new SeqPair(e2, this);
            Value resultValue = newExpr.eval(s);    // if true then do the loop
            return resultValue;
        } else if (((BoolValue) value1).equal(Value.FALSE)) {
            return Value.UNIT;                      // if false then unit
        } else {
            throw new RuntimeError("Loop condition is either TRUE or FALSE");                           // actually never reach here depending on BoolValue's only two instances
        }
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // union of FV(e1) and FV(e2)
        Set<Symbol> result = new HashSet<>(e1.FV());
        result.addAll(e2.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // union of Vars(e1) and Vars(e2)
        Set<Symbol> result = new HashSet<>(e1.Vars());
        result.addAll(e2.Vars());
        return result;
    }

    @Override
    public Loop substitute(Symbol x, Expr e) {
        // TODO
        // while e1[e/x] do e2[e/x]
        return new Loop(e1.substitute(x, e), e2.substitute(x, e));
    }
}
