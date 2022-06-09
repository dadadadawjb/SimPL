package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeVar;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.ListType;

public class ListCase extends Expr {
    public Symbol x1, x2;
    public Expr e, e1, e2;

    public ListCase(Expr e, Expr e1, Symbol x1, Symbol x2, Expr e2) {
        this.e = e;
        this.e1 = e1;
        this.x1 = x1;
        this.x2 = x2;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(case " + e + " of nil => " + e1 + " | " + x1 + "::" + x2 + " => " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult typeResult0 = e.typecheck(E);                                // first check `e`
        Substitution substitution = typeResult0.s;                              // first solve `q0`
        TypeEnv newE0 = substitution.compose(E);                                // update the new type environment for solving the rest type constraints
        TypeResult typeResult1 = e1.typecheck(newE0);                           // then check `e1`
        substitution = substitution.compose(typeResult1.s);                     // then solve `q1`
        TypeEnv newE2_prime = substitution.compose(newE0);                      // update the new type environment for solving the rest type constraints
        TypeVar typeVar1 = new TypeVar(true);                                   // create new type variable, with equality type since not sure about the equality
        Type type1 = substitution.apply(typeVar1);                              // do substitution
        TypeEnv newE1 = TypeEnv.of(newE2_prime, x1, type1);                     // overlap `x1 -> t1` into type environment
        TypeEnv newE2 = TypeEnv.of(newE1, x2, new ListType(type1));             // overlap `x2 -> t1 list` into type environment
        TypeResult typeResult2 = e2.typecheck(newE2);                           // then check `e2`
        substitution = substitution.compose(typeResult2.s);                     // then solve `q2`
        Type type0 = substitution.apply(typeResult0.t);                         // do substitution
        if (type0 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type type1Last = substitution.apply(type1);                             // do substitution
        if (type1Last == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type0.unify(new ListType(type1Last)));      // then solve `t0 = t1 list`
        Type type1_prime = substitution.apply(typeResult1.t);                   // do substitution
        if (type1_prime == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type type2_prime = substitution.apply(typeResult2.t);                   // do substitution
        if (type2_prime == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type1_prime.unify(type2_prime));    // then solve `t1' = t2'`

        Type type1_prime_last = substitution.apply(type1_prime);                // do substitution
        
        return TypeResult.of(substitution, type1_prime_last);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value value0 = e.eval(s);                                       // first the case
        if (value0.equal(Value.NIL)) {
            Value value1 = e1.eval(s);                                  // then `e1`
            return value1;
        } else if (value0 instanceof ConsValue) {
            Env newE = new Env(s.E, x1, ((ConsValue) value0).v1);
            Env newE_prime = new Env(newE, x2, ((ConsValue) value0).v2);
            Value value2 = e2.eval(State.of(newE_prime, s.M, s.p));     // then `e2`
            return value2;
        } else {
            throw new RuntimeError("ListCase requires case NilValue or ConsValue");         // actually never reach here depending on type checking
        }
    }

    @Override
    public Set<Symbol> FV() {
        // union of FV(e) and FV(e1) and (FV(e2) - {x1, x2})
        Set<Symbol> result = new HashSet<>(e2.FV());
        result.remove(x1);
        result.remove(x2);
        result.addAll(e1.FV());
        result.addAll(e.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // union of Vars(e) and Vars(e1) and union of Vars(e2) and {x1, x2}
        Set<Symbol> result = new HashSet<>(e2.Vars());
        result.add(x1);
        result.add(x2);
        result.addAll(e1.Vars());
        result.addAll(e.Vars());
        return result;
    }

    @Override
    public ListCase substitute(Symbol x, Expr e) {
        // 6 cases
        if (x1.equals(x)) {
            return new ListCase(this.e.substitute(x, e), e1.substitute(x, e), x1, x2, e2);
        } else if (x2.equals(x)) {
            return new ListCase(this.e.substitute(x, e), e1.substitute(x, e), x1, x2, e2);
        } else {
            if ((!e.FV().contains(x1) || !e1.FV().contains(x)) && (!e.FV().contains(x2) || !e2.FV().contains(x))) {
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x, e);
                return new ListCase(newExpr0, newExpr1, x1, x2, newExpr2);
            } else if (e.FV().contains(x1) && e1.FV().contains(x) && (!e.FV().contains(x2) || !e2.FV().contains(x))) {
                // need alpha renaming
                Symbol z1 = Symbol.newSymbol();                             // actually z1 \notin FV(e) and z1 \notin FV(e2) and z1 \neq x2 is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x1, new Name(z1)).substitute(x, e);
                return new ListCase(newExpr0, newExpr1, z1, x2, newExpr2);
            } else if ((!e.FV().contains(x1) || !e1.FV().contains(x)) && e.FV().contains(x2) && e2.FV().contains(x)) {
                // need alpha renaming
                Symbol z2 = Symbol.newSymbol();                             // actually z2 \notin FV(e) and z2 \notin FV(e2) and z2 \neq x1 is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x2, new Name(z2)).substitute(x, e);
                return new ListCase(newExpr0, newExpr1, x1, z2, newExpr2);
            } else {
                // need alpha renaming
                Symbol z1 = Symbol.newSymbol();                             // actually z1 \notin FV(e) and z1 \notin FV(e2) and z1 \neq z2 is enough, but directly brand new is easier
                Symbol z2 = Symbol.newSymbol();                             // actually z2 \notin FV(e) and z2 \notin FV(e2) and z2 \neq z1 is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x1, new Name(z1)).substitute(x2, new Name(z2)).substitute(x, e);
                return new ListCase(newExpr0, newExpr1, x1, z2, newExpr2);
            }
        }
    }
}
