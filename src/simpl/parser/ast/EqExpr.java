package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
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
        Type rightType = substitution.apply(rightTypeResult.t);                     // do substitution
        if (rightType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        if (!leftType.isEqualityType() || !rightType.isEqualityType())              // ensure equality type to compare
            throw new TypeMismatchError();
        substitution = substitution.compose(leftType.unify(rightType));             // then solve the type constraint `a1 = a2`

        return TypeResult.of(substitution, Type.BOOL);
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
}
