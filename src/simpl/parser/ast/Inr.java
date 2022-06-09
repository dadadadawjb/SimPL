package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.InrValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeVar;
import simpl.typing.SumType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Inr extends UnaryExpr {

    public Inr(Expr e) {
        super(e);
    }

    public String toString() {
        return "(inr " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult typeResult = e.typecheck(E);         // first check `e`

        Substitution substitution = typeResult.s;       // first solve `q`
        Type type = substitution.apply(typeResult.t);   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        TypeVar t1 = new TypeVar(true);                          // create new type variable, with equality type since not sure about the equality
        Type t1Last = substitution.apply(t1);           // do substitution
        Type resultType = new SumType(t1Last, type);    // create result type

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value value = e.eval(s);
        return new InrValue(value);
    }

    @Override
    public Set<Symbol> FV() {
        // FV(e)
        return e.FV();
    }

    @Override
    public Set<Symbol> Vars() {
        // Vars(e)
        return e.Vars();
    }

    @Override
    public Inr substitute(Symbol x, Expr e) {
        // inr e'[e/x]
        return new Inr(this.e.substitute(x, e));
    }
}
