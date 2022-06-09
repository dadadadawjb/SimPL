package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult leftTypeResult = l.typecheck(E);                                 // first check left
        TypeEnv newE = leftTypeResult.s.compose(E);                                 // update the new type environment for solving the rest type constraints
        TypeResult rightTypeResult = r.typecheck(newE);                             // then check right

        Substitution substitution = leftTypeResult.s.compose(rightTypeResult.s);    // first solve left `q1` then right `q2`
        Type leftType = substitution.apply(leftTypeResult.t);                       // do substitution
        if (leftType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(leftType.unify(Type.BOOL));             // then solve the type constraint `t1 = bool`
        Type rightType = substitution.apply(rightTypeResult.t);                     // do substitution
        if (rightType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(rightType.unify(Type.BOOL));            // then solve the type constraint `t2 = bool`

        return TypeResult.of(substitution, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value leftValue = l.eval(s);        // first left
        if (!(leftValue instanceof BoolValue))
            throw new RuntimeError("OrElse requires left BoolValue");                           // actually never reach here depending on type checking
        if (((BoolValue) leftValue).equal(Value.TRUE)) {
            return Value.TRUE;              // no need evaluate right
        } else if (((BoolValue) leftValue).equal(Value.FALSE)) {
            Value rightValue = r.eval(s);   // then right
            if (!(rightValue instanceof BoolValue))
                throw new RuntimeError("OrElse requires right BoolValue");                          // actually never reach here depending on type checking
            return rightValue;
        } else {
            throw new RuntimeError("OrElse left is either TRUE or FALSE");                           // actually never reach here depending on BoolValue's only two instances
        }
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
    public OrElse substitute(Symbol x, Expr e) {
        // l[e/x] orelse r[e/x]
        return new OrElse(l.substitute(x, e), r.substitute(x, e));
    }
}
