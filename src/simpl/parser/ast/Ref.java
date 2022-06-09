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
        // IMPORTANT
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
        // IMPORTANT
        int ptr = s.M.alloc(s);             // malloc
        Value value = e.eval(s);            // evaluate value
        s.M.write(ptr, value);              // write to memory
        return new RefValue(ptr);
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
    public Ref substitute(Symbol x, Expr e) {
        // ref e'[e/x]
        return new Ref(this.e.substitute(x, e));
    }
}
