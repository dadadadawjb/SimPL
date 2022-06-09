package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        Type type = E.get(x);
        if (type == null)
            throw new TypeError("Symbol " + x + " not found in type environment");
        
        return TypeResult.of(type);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value value = s.E.get(x);                                                   // get the value of the symbol
        if (value == null)
            throw new RuntimeError("Symbol " + x + " not found in state");
        if (value instanceof RecValue) {
            RecValue recValue = (RecValue) value;
            Expr newExpr = new Rec(recValue.x, recValue.e);
            Value resultValue = newExpr.eval(State.of(recValue.E, s.M, s.p));       // if rec then evaluate recursion
            return resultValue;
        } else {
            return value;                                                           // if not rec then directly return the value
        }
    }

    @Override
    public Set<Symbol> FV() {
        // {x}
        Set<Symbol> result = new HashSet<>();
        result.add(x);
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // {x}
        Set<Symbol> result = new HashSet<>();
        result.add(x);
        return result;
    }

    @Override
    public Expr substitute(Symbol x, Expr e) {
        // 2 cases
        return (this.x.equals(x)) ? e : this;
    }
}
