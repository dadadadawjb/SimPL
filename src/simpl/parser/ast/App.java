package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult leftTypeResult = l.typecheck(E);                                                     // first check left
        TypeEnv newE = leftTypeResult.s.compose(E);                                                     // update the new type environment for solving the rest type constraints
        TypeResult rightTypeResult = r.typecheck(newE);                                                 // then check right

        Substitution substitution = leftTypeResult.s.compose(rightTypeResult.s);                        // first solve left `q1` then right `q2`
        Type leftType = substitution.apply(leftTypeResult.t);                                           // do substitution
        if (leftType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type rightType = substitution.apply(rightTypeResult.t);                                         // do substitution
        if (rightType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        TypeVar resultTypeVar = new TypeVar(true);                                                      // create new type variable, with equality type since not sure about the equality
        substitution = substitution.compose(leftType.unify(new ArrowType(rightType, resultTypeVar)));   // then solve the type constraint `t1 = t2 -> t3`
        Type resultType = substitution.apply(resultTypeVar);                                            // do substitution

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value leftValue = l.eval(s);        // first left
        if (!(leftValue instanceof FunValue))
            throw new RuntimeError("App requires left FunValue");                           // actually never reach here depending on type checking
        Value rightValue = r.eval(s);       // then right
        FunValue leftFunValue = (FunValue) leftValue;
        Env newE = new Env(leftFunValue.E, leftFunValue.x, rightValue);
        Value resultValue = leftFunValue.e.eval(State.of(newE, s.M, s.p));      // then evaluate the function body
        return resultValue;
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
    public App substitute(Symbol x, Expr e) {
        // TODO
        // l[e/x] r[e/x]
        return new App(l.substitute(x, e), r.substitute(x, e));
    }
}
