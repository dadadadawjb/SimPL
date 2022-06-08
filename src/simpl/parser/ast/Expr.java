package simpl.parser.ast;

import java.util.Set;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class Expr {

    // Type check
    public abstract TypeResult typecheck(TypeEnv E) throws TypeError;

    // Evaluation, rely on the side effects on the state
    public abstract Value eval(State s) throws RuntimeError;

    // Return the free variables this expression contains
    public abstract Set<Symbol> FV();

    // Return the all variables this expression contains
    public abstract Set<Symbol> Vars();

    // Substitute a variable contained in this expression to another expression and return back the new expression without modifying this expression
    public abstract Expr substitute(Symbol x, Expr e);
}
