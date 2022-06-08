package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.InlValue;
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

public class Inl extends UnaryExpr {

    public Inl(Expr e) {
        super(e);
    }

    public String toString() {
        return "(inl " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult = e.typecheck(E);         // first check `e`

        Substitution substitution = typeResult.s;       // first solve `q`
        Type type = substitution.apply(typeResult.t);   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        TypeVar t2 = new TypeVar(true);                          // create new type variable, with equality type since not sure about the equality
        Type t2Last = substitution.apply(t2);           // do substitution
        Type resultType = new SumType(type, t2Last);    // create result type

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value = e.eval(s);
        return new InlValue(value);
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // FV(e)
        return e.FV();
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // Vars(e)
        return e.Vars();
    }

    @Override
    public Inl substitute(Symbol x, Expr e) {
        // TODO
        // inl e'[e/x]
        return new Inl(this.e.substitute(x, e));
    }
}
