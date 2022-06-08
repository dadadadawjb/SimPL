package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult typeResult1 = e1.typecheck(E);                                                   // first check the first
        TypeEnv newE1 = typeResult1.s.compose(E);                                                   // update the new type environment for solving the rest type constraints
        TypeResult typeResult2 = e2.typecheck(newE1);                                               // second check the second
        TypeEnv newE2 = typeResult2.s.compose(newE1);                                               // update the new type environment for solving the rest type constraints
        TypeResult typeResult3 = e3.typecheck(newE2);                                               // third check the third
        
        Substitution substitution = typeResult1.s.compose(typeResult2.s).compose(typeResult3.s);    // first solve `q1` second `q2` third `q3`
        Type type1 = substitution.apply(typeResult1.t);                                             // do substitution
        if (type1 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type1.unify(Type.BOOL));                                // then solve the type constraint `t1 = bool`
        Type type2 = substitution.apply(typeResult2.t);                                             // do substitution
        if (type2 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        Type type3 = substitution.apply(typeResult3.t);                                             // do substitution
        if (type3 == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        substitution = substitution.compose(type2.unify(type3));                                    // then solve the type constraint `t2 = t3`
        Type resultType = substitution.apply(type2);                                                // do substitution
        if (resultType == null)
            throw new TypeError("Some symbol not found in type environment");       // actually never reach here since type error only occurs in name expression
        
        return TypeResult.of(substitution, resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value value1 = e1.eval(s);          // first the first
        if (!(value1 instanceof BoolValue))
            throw new RuntimeError("Cond requires the first BoolValue");                            // actually never reach here depending on type checking
        if (((BoolValue) value1).equals(Value.TRUE))
            return e2.eval(s);              // if true then the second
        else if (((BoolValue) value1).equals(Value.FALSE))
            return e3.eval(s);              // if false then the third
        else
            throw new RuntimeError("Cond first is either TRUE or FALSE");                           // actually never reach here depending on type checking
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // union of FV(e1) and FV(e2) and FV(e3)
        Set<Symbol> result = new HashSet<>(e1.FV());
        result.addAll(e2.FV());
        result.addAll(e3.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // union of Vars(e1) and Vars(e2) and Vars(e3)
        Set<Symbol> result = new HashSet<>(e1.Vars());
        result.addAll(e2.Vars());
        result.addAll(e3.Vars());
        return result;
    }

    @Override
    public Cond substitute(Symbol x, Expr e) {
        // TODO
        // if e1[e/x] then e2[e/x] else e3[e/x]
        return new Cond(e1.substitute(x, e), e2.substitute(x, e), e3.substitute(x, e));
    }
}
