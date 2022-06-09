package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.Env;
import simpl.interpreter.InlValue;
import simpl.interpreter.InrValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.SumType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class SumCase extends Expr {
    public Symbol x1, x2;
    public Expr e, e1, e2;

    public SumCase(Expr e, Symbol x1, Expr e1, Symbol x2, Expr e2) {
        this.e = e;
        this.x1 = x1;
        this.x2 = x2;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(case " + e + " of inl " + x1 + " => " + e1 + " | inr " + x2 + " => " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        TypeResult typeResult0 = e.typecheck(E);                                // first check `e`
        Substitution substitution = typeResult0.s;                              // first solve `q0`
        TypeEnv newE0 = substitution.compose(E);                                // update the new type environment for solving the rest type constraints
        TypeVar typeVar1 = new TypeVar(true);                                   // create new type variable, with equality type since not sure about the equality
        Type type1 = substitution.apply(typeVar1);                              // do substitution
        TypeEnv newE1 = TypeEnv.of(newE0, x1, type1);                           // overlap `x1 -> t1` into type environment
        TypeResult typeResult1 = e1.typecheck(newE1);                           // then check `e1`
        substitution = substitution.compose(typeResult1.s);                     // then solve `q1`
        TypeEnv newE2_prime = substitution.compose(newE0);                      // update the new type environment for solving the rest type constraints
        TypeVar typeVar2 = new TypeVar(true);                                   // create new type variable, with equality type since not sure about the equality
        Type type2 = substitution.apply(typeVar2);                              // do substitution
        TypeEnv newE2 = TypeEnv.of(newE2_prime, x2, type2);                     // overlap `x2 -> t2` into type environment
        TypeResult typeResult2 = e2.typecheck(newE2);                           // then check `e2`
        substitution = substitution.compose(typeResult2.s);                     // then solve `q2`
        Type type0 = substitution.apply(typeResult0.t);                         // do substitution
        if (type0 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type type1Last = substitution.apply(type1);                             // do substitution
        if (type1Last == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type type2Last = substitution.apply(type2);                             // do substitution
        if (type2Last == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type0.unify(new SumType(type1Last, type2Last)));    // then solve `t0 = t1 + t2`
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
        Value value0 = e.eval(s);                               // first the case
        if (value0 instanceof InlValue) {
            Env newE = new Env(s.E, x1, ((InlValue) value0).v);
            Value value1 = e1.eval(State.of(newE, s.M, s.p));   // then `e1`
            return value1;
        } else if (value0 instanceof InrValue) {
            Env newE = new Env(s.E, x2, ((InrValue) value0).v);
            Value value2 = e2.eval(State.of(newE, s.M, s.p));   // then `e2`
            return value2;
        } else {
            throw new RuntimeError("SumCase requires case InlValue or InrValue");           // actually never reach here depending on type checking
        }
    }

    @Override
    public Set<Symbol> FV() {
        // union of FV(e) and (FV(e1) - {x1}) and (FV(e2) - {x2})
        Set<Symbol> result = new HashSet<>(e.FV());
        Set<Symbol> temp1 = new HashSet<>(e1.FV());
        temp1.remove(x1);
        Set<Symbol> temp2 = new HashSet<>(e2.FV());
        temp2.remove(x2);
        result.addAll(temp1);
        result.addAll(temp2);
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // union of Vars(e) and union of Vars(e1) and {x1} and union of Vars(e2) and {x2}
        Set<Symbol> result = new HashSet<>(e.Vars());
        Set<Symbol> temp1 = new HashSet<>(e1.Vars());
        temp1.add(x1);
        Set<Symbol> temp2 = new HashSet<>(e2.Vars());
        temp2.add(x2);
        result.addAll(temp1);
        result.addAll(temp2);
        return result;
    }

    @Override
    public SumCase substitute(Symbol x, Expr e) {
        // 6 cases
        if (x1.equals(x)) {
            return new SumCase(this.e.substitute(x, e), x1, e1, x2, e2.substitute(x, e));
        } else if (x2.equals(x)) {
            return new SumCase(this.e.substitute(x, e), x1, e1.substitute(x, e), x2, e2);
        } else {
            if ((!e.FV().contains(x1) || !e1.FV().contains(x)) && (!e.FV().contains(x2) || !e2.FV().contains(x))) {
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x, e);
                return new SumCase(newExpr0, x1, newExpr1, x2, newExpr2);
            } else if (e.FV().contains(x1) && e1.FV().contains(x) && (!e.FV().contains(x2) || !e2.FV().contains(x))) {
                // need alpha renaming
                Symbol z1 = Symbol.newSymbol();                             // actually z1 \notin FV(e) and z1 \notin FV(e1) is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x1, new Name(z1)).substitute(x, e);
                Expr newExpr2 = e2.substitute(x, e);
                return new SumCase(newExpr0, z1, newExpr1, x2, newExpr2);
            } else if ((!e.FV().contains(x1) || !e1.FV().contains(x)) && e.FV().contains(x2) && e2.FV().contains(x)) {
                // need alpha renaming
                Symbol z2 = Symbol.newSymbol();                             // actually z2 \notin FV(e) and z2 \notin FV(e2) is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x2, new Name(z2)).substitute(x, e);
                return new SumCase(newExpr0, x1, newExpr1, z2, newExpr2);
            } else {
                // need alpha renaming
                Symbol z1 = Symbol.newSymbol();                             // actually z1 \notin FV(e) and z1 \notin FV(e1) is enough, but directly brand new is easier
                Symbol z2 = Symbol.newSymbol();                             // actually z2 \notin FV(e) and z2 \notin FV(e2) is enough, but directly brand new is easier
                Expr newExpr0 = this.e.substitute(x, e);
                Expr newExpr1 = e1.substitute(x1, new Name(z1)).substitute(x, e);
                Expr newExpr2 = e2.substitute(x2, new Name(z2)).substitute(x, e);
                return new SumCase(newExpr0, z1, newExpr1, z2, newExpr2);
            }
        }
    }
}
