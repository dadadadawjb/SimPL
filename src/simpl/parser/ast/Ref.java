package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult = e.typecheck(E);         // first check `e`

        Substitution substitution = typeResult.s;       // first solve `q`
        Type type = substitution.apply(typeResult.t);   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type resultType = new RefType(type);            // create result type

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int i = s.p.get();
        s.p.set(i + 1);
        Value value = e.eval(s);            // first the body
        s.M.put(i, value);                  // update the memory
        return new RefValue(i);
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
    public Ref substitute(Symbol x, Expr e) {
        // TODO
        // ref e'[e/x]
        return new Ref(this.e.substitute(x, e));
    }
}
