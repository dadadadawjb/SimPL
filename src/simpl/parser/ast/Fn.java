package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeVar t1 = new TypeVar(true);                          // create new type variable, with equality type since not sure about the equality
        TypeEnv newE = TypeEnv.of(E, x, t1);                    // overlap x->t1 into E
        TypeResult typeResult = e.typecheck(newE);              // first check `e`

        Substitution substitution = typeResult.s;               // first solve `q`
        Type t2 = substitution.apply(typeResult.t);             // do substitution
        if (t2 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type t1Last = substitution.apply(t1);                   // do substitution
        if (t1Last == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression

        return TypeResult.of(substitution, new ArrowType(t1Last, t2));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        return new FunValue(s.E.clone(), x, e);
    }

    @Override
    public Set<Symbol> FV() {
        // FV(e) - {x}
        Set<Symbol> result = new HashSet<>(e.FV());
        result.remove(x);
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // union of Vars(e) and {x}
        Set<Symbol> result = new HashSet<>(e.Vars());
        result.add(x);
        return result;
    }

    @Override
    public Fn substitute(Symbol x, Expr e) {
        // 3 cases
        if (this.x.equals(x)) {
            return this;
        } else {
            if (!e.FV().contains(this.x) || !this.e.FV().contains(x)) {
                Expr newExpr = this.e.substitute(x, e);
                return new Fn(this.x, newExpr);
            } else {
                // need alpha renaming
                Symbol z = Symbol.newSymbol();                              // actually z \notin FV(e) and z \notin FV(this.e) is enough, but directly brand new is easier
                Expr newExpr = this.e.substitute(this.x, new Name(z)).substitute(x, e);
                return new Fn(z, newExpr);
            }
        }
    }
}
