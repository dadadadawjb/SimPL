package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.PolyType;

public class Let extends Expr {
    private static final int LET_POLYMORPHISM = 2;      // 0 for no let-polymorphism, 1 for substitution based let-polymorphism, 2 for universal type variable based let-polymorphism

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        switch (LET_POLYMORPHISM) {
            case 0: {
                // not Let-Polymorphism
                TypeResult typeResult1 = e1.typecheck(E);                               // first check `e1`
                TypeEnv newE1 = typeResult1.s.compose(E);                               // update the new type environment for solving the rest type constraints
                Type type1 = typeResult1.s.apply(typeResult1.t);                        // do substitution
                TypeEnv newE2 = TypeEnv.of(newE1, x, type1);                            // overlap `x -> t1` into type environment
                TypeResult typeResult2 = e2.typecheck(newE2);                           // then check `e2`
                
                Substitution substitution = typeResult1.s.compose(typeResult2.s);       // first solve left `q1` then right `q2`
                Type type2 = substitution.apply(typeResult2.t);                         // do substitution
                
                return TypeResult.of(substitution, type2);
            }
            case 1: {
                // Let-Polymorphism, substitution
                TypeResult typeResult1 = e1.typecheck(E);                               // first check `e1`
                TypeEnv newE = typeResult1.s.compose(E);                                // update the new type environment for solving the rest type constraints
                TypeResult typeResult2 = e2.substitute(x, e1).typecheck(newE);          // then check `e2[e1/x]`
                
                Substitution substitution = typeResult1.s.compose(typeResult2.s);       // first solve left `q1` then right `q2`
                Type type2 = substitution.apply(typeResult2.t);                         // do substitution
                
                return TypeResult.of(substitution, type2);
            }
            case 2: {
                // Let-Polymorphism, universal polymorphism
                TypeResult typeResult1 = e1.typecheck(E);                               // first check `e1`
                TypeEnv newE1 = typeResult1.s.compose(E);                               // update the new type environment for solving the rest type constraints
                Type type1 = typeResult1.s.apply(typeResult1.t);                        // do substitution
                PolyType type1Last = new PolyType(type1, newE1);                        // create universal polymorphism type
                TypeEnv newE2 = TypeEnv.of(newE1, x, type1Last);                        // overlap `x -> t1` into type environment
                TypeResult typeResult2 = e2.typecheck(newE2);                           // then check `e2`

                Substitution substitution = typeResult1.s.compose(typeResult2.s);       // first solve left `q1` then right `q2`
                Type type2 = substitution.apply(typeResult2.t);                         // do substitution
                
                return TypeResult.of(substitution, type2);
            }
            default:
                throw new TypeError("Unsupported Let-Polymorphism");                // actually never reach here
        }
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value1 = e1.eval(s);                              // first the condition
        Env newE = new Env(s.E, x, value1);
        Value value2 = e2.eval(State.of(newE, s.M, s.p));       // then the body
        return value2;
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // union of FV(e1) and (FV(e2) - {x})
        Set<Symbol> result = new HashSet<>(e2.FV());
        result.remove(x);
        result.addAll(e1.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // union of Vars(e1) and union of Vars(e2) and {x}
        Set<Symbol> result = new HashSet<>(e2.Vars());
        result.add(x);
        result.addAll(e1.Vars());
        return result;
    }

    @Override
    public Let substitute(Symbol x, Expr e) {
        // TODO
        // 2 cases
        if (this.x.equals(x)) {
            return new Let(this.x, e1.substitute(x, e), e2);
        } else {
            if (!e.FV().contains(this.x) || !e2.FV().contains(x)) {
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(x, e);
                return new Let(this.x, newExpr1, newExpr2);
            } else {
                // need alpha renaming
                Symbol z = Symbol.newSymbol();                              // actually z \notin FV(e) and z \notin FV(e2) is enough, but directly brand new is easier
                Expr newExpr1 = e1.substitute(x, e);
                Expr newExpr2 = e2.substitute(this.x, new Name(z)).substitute(x, e);
                return new Let(z, newExpr1, newExpr2);
            }
        }
    }
}
