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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
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
        substitution = substitution.compose(leftType.unify(Type.BOOL));             // then solve the type constraint `t1 = bool`
        Type rightType = substitution.apply(rightTypeResult.t);                     // do substitution
        if (rightType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(rightType.unify(Type.BOOL));            // then solve the type constraint `t2 = bool`

        return TypeResult.of(substitution, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value leftValue = l.eval(s);        // first left
        if (!(leftValue instanceof BoolValue))
            throw new RuntimeError("AndAlso requires left BoolValue");          // actually never reach here depending on type checking
        if (((BoolValue) leftValue).equals(Value.TRUE)) {
            Value rightValue = r.eval(s);   // then right
            if (!(rightValue instanceof BoolValue))
                throw new RuntimeError("AndAlso requires right BoolValue");         // actually never reach here depending on type checking
            return rightValue;
        } else {
            return Value.FALSE;             // no need evaluate right
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
    public AndAlso substitute(Symbol x, Expr e) {
        // TODO
        // l[e/x] andalso r[e/x]
        return new AndAlso(l.substitute(x, e), r.substitute(x, e));
    }
}