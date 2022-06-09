package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult leftTypeResult = l.typecheck(E);                                     // first check left
        TypeEnv newE = leftTypeResult.s.compose(E);                                     // update the new type environment for solving the rest type constraints
        TypeResult rightTypeResult = r.typecheck(newE);                                 // then check right

        Substitution substitution = leftTypeResult.s.compose(rightTypeResult.s);        // first solve left `q1` then right `q2`
        Type leftType = substitution.apply(leftTypeResult.t);                           // do substitution
        if (leftType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type rightType = substitution.apply(rightTypeResult.t);                         // do substitution
        if (rightType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(rightType.unify(new ListType(leftType)));   // then solve the type constraint `t2 = t1 list`
        Type resultType = substitution.apply(rightTypeResult.t);                        // do substitution

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value leftValue = l.eval(s);        // first left
        Value rightValue = r.eval(s);       // then right
        if (!(rightValue instanceof ConsValue) && !(rightValue.equal(Value.NIL)))
            throw new RuntimeError("Cons requires right ConsValue or NilValue");            // actually never reach here depending on type checking
        return new ConsValue(leftValue, rightValue);
    }

    @Override
    public Set<Symbol> FV() {
        // union of FV(l) and FV(r)
        Set<Symbol> result = new HashSet<>(l.FV());
        result.addAll(r.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // union of Vars(l) and Vars(r)
        Set<Symbol> result = new HashSet<>(l.Vars());
        result.addAll(r.Vars());
        return result;
    }

    @Override
    public Cons substitute(Symbol x, Expr e) {
        // l[e/x] :: r[e/x]
        return new Cons(l.substitute(x, e), r.substitute(x, e));
    }
}
