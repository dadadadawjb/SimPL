package simpl.parser.ast;

import java.util.Set;
import java.util.HashSet;
import simpl.parser.Symbol;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class SeqPair extends BinaryExpr {

    public SeqPair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "<seqpair " + l + " " + r + ">";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // IMPORTANT
        return TypeResult.of(Type.UNIT);                // actually never reach here since it never appears in source codes
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // IMPORTANT
        Value leftValue = l.eval(s);            // first left
        if (leftValue.equal(Value.BREAK)) {
            return Value.UNIT;                  // directly unit
        } else if (leftValue.equal(Value.CONTINUE)) {
            Value rightValue = r.eval(s);       // then right
            return rightValue;
        } else if (leftValue.equal(Value.UNIT)) {
            Value rightValue = r.eval(s);       // then right
            return rightValue;
        } else {
            throw new RuntimeError("SeqPair left requires to be either BREAK, CONTINUE or UNIT");       // actually never reach here depending on while's operational semantics and typing rule
        }
    }

    @Override
    public Set<Symbol> FV() {
        // union of FV(l) and FV(r)
        Set<Symbol> result = new HashSet<>(l.FV());
        result.addAll(r.FV());
        return result;
    }

    @Override
    public Set<Symbol> Vars() {
        // union of Vars(l) and Vars(r)
        Set<Symbol> result = new HashSet<>(l.Vars());
        result.addAll(r.Vars());
        return result;
    }

    @Override
    public SeqPair substitute(Symbol x, Expr e) {
        // (l[e/x], r[e/x])
        return new SeqPair(l.substitute(x, e), r.substitute(x, e));
    }
}
