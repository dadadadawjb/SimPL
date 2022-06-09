package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult typeResult = e.typecheck(E);                         // first check `e`

        Substitution substitution = typeResult.s;                       // first solve `q`
        Type type = substitution.apply(typeResult.t);                   // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type.unify(Type.INT));      // then solve the type constraint `t = int`

        return TypeResult.of(substitution, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value value = e.eval(s);            // first the body
        if (!(value instanceof IntValue))
            throw new RuntimeError("Neg requires IntValue");                            // actually never reach here depending on type checking
        return new IntValue(-((IntValue) value).n);
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
    public Neg substitute(Symbol x, Expr e) {
        // ~e'[e/x]
        return new Neg(this.e.substitute(x, e));
    }
}
