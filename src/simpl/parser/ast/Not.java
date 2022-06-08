package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult = e.typecheck(E);                         // first check `e`

        Substitution substitution = typeResult.s;                       // first solve `q`
        Type type = substitution.apply(typeResult.t);                   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type.unify(Type.BOOL));     // then solve the type constraint `t = bool`

        return TypeResult.of(substitution, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value = e.eval(s);            // first the body
        if (!(value instanceof BoolValue))
            throw new RuntimeError("Not requires BoolValue");                            // actually never reach here depending on type checking
        if (((BoolValue) value).equal(Value.TRUE))
            return Value.FALSE;
        else if (((BoolValue) value).equal(Value.FALSE))
            return Value.TRUE;
        else
            throw new RuntimeError("Not body is either TRUE or FALSE");                           // actually never reach here depending on BoolValue's only two instances
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
    public Not substitute(Symbol x, Expr e) {
        // TODO
        // not e'[e/x]
        return new Not(this.e.substitute(x, e));
    }
}
