package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult = e.typecheck(E);                                         // first check `e`

        Substitution substitution = typeResult.s;                                       // first solve `q`
        TypeVar resultTypeVar = new TypeVar(true);                                      // create new type variable, with equality type since not sure about the equality
        Type type = substitution.apply(typeResult.t);                                   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type.unify(new RefType(resultTypeVar)));    // then solve the type constraint `t = t' ref`
        Type resultType = substitution.apply(resultTypeVar);                            // do substitution
        if (resultType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value = e.eval(s);        // first the body
        if (!(value instanceof RefValue))
            throw new RuntimeError("Deref requires the body RefValue");                             // actually never reach here depending on type checking
        Value resultValue = s.M.get(((RefValue) value).p);      // then get the value from the memory
        if (resultValue == null)
            throw new RuntimeError("Deref out of memory pointer");
        return resultValue;
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
    public Deref substitute(Symbol x, Expr e) {
        // TODO
        // !e'[e/x]
        return new Deref(this.e.substitute(x, e));
    }
}
