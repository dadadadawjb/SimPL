package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.ListType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar typeVar = new TypeVar(true);                // create new type variable with equality type
        Type resultType = new ListType(typeVar);            // create result type
        
        return TypeResult.of(resultType);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return Value.NIL;
    }

    @Override
    public Set<Symbol> FV() {
        // TODO
        // empty set
        return new HashSet<>();
    }

    @Override
    public Set<Symbol> Vars() {
        // TODO
        // empty set
        return new HashSet<>();
    }

    @Override
    public Nil substitute(Symbol x, Expr e) {
        // TODO
        // not substitute
        return this;
    }
}
