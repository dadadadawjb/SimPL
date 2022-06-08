package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult leftTypeResult = l.typecheck(E);                                 // first check left
        TypeEnv newE = leftTypeResult.s.compose(E);                                 // update the new type environment for solving the rest type constraints
        TypeResult rightTypeResult = r.typecheck(newE);                             // then check right

        Substitution substitution = leftTypeResult.s.compose(rightTypeResult.s);    // first solve left `q1` then right `q2`
        Type leftType = substitution.apply(leftTypeResult.t);                       // do substitution
        if (leftType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(leftType.unify(Type.UNIT));             // then solve the type constraint `t1 = unit`
        Type rightType = substitution.apply(rightTypeResult.t);                     // do substitution

        return TypeResult.of(substitution, rightType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value leftValue = l.eval(s);        // first left
        if (leftValue.equal(Value.UNIT)) {
            Value rightValue = r.eval(s);   // then right
            return rightValue;
        } else if (leftValue.equal(Value.BREAK)) {
            return Value.BREAK;             // directly break
        } else if (leftValue.equal(Value.CONTINUE)) {
            return Value.CONTINUE;          // directly continue
        } else {
            throw new RuntimeError("Seq left requires either BREAK, CONTINUE or UNIT");                           // actually never reach here depending on type checking
        }
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // union of FV(l) and FV(r)
        Set<Symbol> result = new HashSet<>(l.FV());
        result.addAll(r.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // union of Vars(l) and Vars(r)
        Set<Symbol> result = new HashSet<>(l.Vars());
        result.addAll(r.Vars());
        return result;
    }

    @Override
    public Seq substitute(Symbol x, Expr e) {
        // TODO
        // l[e/x] ; r[e/x]
        return new Seq(l.substitute(x, e), r.substitute(x, e));
    }
}
