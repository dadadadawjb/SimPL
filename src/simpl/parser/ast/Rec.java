package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar a = new TypeVar(true);                              // create new type variable, with equality type since not sure about the equality
        TypeEnv newE = TypeEnv.of(E, x, a);                         // overlap `x -> a` into type environment
        TypeResult typeResult = e.typecheck(newE);                  // first check `e`

        Substitution substitution = typeResult.s;                   // first solve `q`
        Type type = substitution.apply(typeResult.t);               // do substitution
        if (type == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type aType = substitution.apply(a);                         // do substitution
        if (aType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type.unify(aType));     // then solve the type constraint `t = a`
        Type resultType = substitution.apply(type);                 // do substitution
        if (resultType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression

        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Env newE = new Env(s.E, x, new RecValue(s.E.clone(), x, e));
        Value resultValue = e.eval(State.of(newE, s.M, s.p));           // evaluate the body
        return resultValue;
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // FV(e) - {x}
        Set<Symbol> result = new HashSet<>(e.FV());
        result.remove(x);
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // union of Vars(e) and {x}
        Set<Symbol> result = new HashSet<>(e.Vars());
        result.add(x);
        return result;
    }

    @Override
    public Rec substitute(Symbol x, Expr e) {
        // TODO
        // 3 cases
        if (x.equals(this.x)) {
            return this;
        } else {
            if (!e.FV().contains(this.x) || !this.e.FV().contains(x)) {
                Expr newExpr = this.e.substitute(x, e);
                return new Rec(this.x, newExpr);
            } else {
                // need alpha renaming
                Symbol z = Symbol.newSymbol();                              // actually z \notin FV(e) and z \notin FV(this.e) is enough, but directly brand new is easier
                Expr newExpr = this.e.substitute(this.x, new Name(z)).substitute(x, e);
                return new Rec(z, newExpr);
            }
        }
    }
}
