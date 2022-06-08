package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            // parse
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            
            // type check
            TypeEnv typeEnv = new DefaultTypeEnv();
            TypeResult typeResult = program.typecheck(typeEnv);
            System.out.println(typeResult.t);

            // evaluate
            try {
                State state = new InitialState();
                Value result = program.eval(state);
                System.out.println(result.toString(state));
            } catch (StackOverflowError e) {
                System.out.println("stack overflow");
            }
        }
        catch (SyntaxError e) {
            System.out.println("syntax error");
        }
        catch (TypeError e) {
            System.out.println("type error");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java -jar SimPL.jar <filepath>");
            System.exit(1);
        } else {
            if (args[0].equals("examples")) {
                interpret("doc/examples/basics/name0_error.spl");
                interpret("doc/examples/basics/app0.spl");
                interpret("doc/examples/basics/app0_error.spl");
                interpret("doc/examples/basics/fn0.spl");
                interpret("doc/examples/basics/fn1.spl");
                interpret("doc/examples/basics/fn0_error.spl");
                interpret("doc/examples/basics/cond0.spl");
                interpret("doc/examples/basics/cond0_error.spl");
                interpret("doc/examples/basics/and0.spl");
                interpret("doc/examples/basics/and1.spl");
                interpret("doc/examples/basics/and2.spl");
                interpret("doc/examples/basics/and0_error.spl");
                interpret("doc/examples/basics/or0.spl");
                interpret("doc/examples/basics/or1.spl");
                interpret("doc/examples/basics/or2.spl");
                interpret("doc/examples/basics/or0_error.spl");
                interpret("doc/examples/basics/not0.spl");
                interpret("doc/examples/basics/not0_error.spl");
                interpret("doc/examples/basics/add0.spl");
                interpret("doc/examples/basics/add0_error.spl");
                interpret("doc/examples/basics/sub0.spl");
                interpret("doc/examples/basics/sub0_error.spl");
                interpret("doc/examples/basics/mul0.spl");
                interpret("doc/examples/basics/mul0_error.spl");
                interpret("doc/examples/basics/div0.spl");
                interpret("doc/examples/basics/div0_error.spl");
                interpret("doc/examples/basics/div1_error.spl");
                interpret("doc/examples/basics/mod0.spl");
                interpret("doc/examples/basics/mod0_error.spl");
                interpret("doc/examples/basics/mod1_error.spl");
                interpret("doc/examples/basics/neg0.spl");
                interpret("doc/examples/basics/neg0_error.spl");
                interpret("doc/examples/basics/eq0.spl");
                interpret("doc/examples/basics/eq1.spl");
                interpret("doc/examples/basics/eq2.spl");
                interpret("doc/examples/basics/eq3.spl");
                interpret("doc/examples/basics/eq0_error.spl");
                interpret("doc/examples/basics/eq4_error.spl");
                interpret("doc/examples/basics/eq5_error.spl");
                interpret("doc/examples/basics/neq0.spl");
                interpret("doc/examples/basics/neq1.spl");
                interpret("doc/examples/basics/neq2.spl");
                interpret("doc/examples/basics/neq3.spl");
                interpret("doc/examples/basics/neq0_error.spl");
                interpret("doc/examples/basics/neq4_error.spl");
                interpret("doc/examples/basics/neq5_error.spl");
                interpret("doc/examples/basics/less0.spl");
                interpret("doc/examples/basics/less1.spl");
                interpret("doc/examples/basics/less0_error.spl");
                interpret("doc/examples/basics/less1_error.spl");
                interpret("doc/examples/basics/lesseq0.spl");
                interpret("doc/examples/basics/lesseq1.spl");
                interpret("doc/examples/basics/lesseq0_error.spl");
                interpret("doc/examples/basics/lesseq1_error.spl");
                interpret("doc/examples/basics/greater0.spl");
                interpret("doc/examples/basics/greater1.spl");
                interpret("doc/examples/basics/greater0_error.spl");
                interpret("doc/examples/basics/greater1_error.spl");
                interpret("doc/examples/basics/greatereq0.spl");
                interpret("doc/examples/basics/greatereq1.spl");
                interpret("doc/examples/basics/greatereq0_error.spl");
                interpret("doc/examples/basics/greatereq1_error.spl");
                interpret("doc/examples/basics/let0.spl");
                interpret("doc/examples/basics/let1.spl");
                interpret("doc/examples/basics/pair0.spl");
                interpret("doc/examples/basics/pair1.spl");
                interpret("doc/examples/basics/pair2.spl");
                interpret("doc/examples/basics/pair3.spl");
                interpret("doc/examples/basics/inl0.spl");
                interpret("doc/examples/basics/inr0.spl");
                interpret("doc/examples/basics/sumcase0.spl");
                interpret("doc/examples/basics/sumcase1.spl");
                interpret("doc/examples/basics/rec0.spl");
                interpret("doc/examples/basics/nil0.spl");
                interpret("doc/examples/basics/cons0.spl");
                interpret("doc/examples/basics/cons0_error.spl");
                interpret("doc/examples/basics/listcase0.spl");
                interpret("doc/examples/basics/listcase1.spl");
                interpret("doc/examples/basics/ref0.spl");
                interpret("doc/examples/basics/ref1.spl");
                interpret("doc/examples/basics/ref2.spl");
                interpret("doc/examples/basics/deref0.spl");
                interpret("doc/examples/basics/deref1.spl");
                interpret("doc/examples/basics/deref0_error.spl");
                interpret("doc/examples/basics/assign0.spl");
                interpret("doc/examples/basics/assign0_error.spl");
                interpret("doc/examples/basics/seq0.spl");
                interpret("doc/examples/basics/seq0_error.spl");
                interpret("doc/examples/basics/loop0.spl");
                interpret("doc/examples/basics/break0.spl");
                interpret("doc/examples/basics/break1.spl");
                interpret("doc/examples/basics/break2.spl");
                interpret("doc/examples/basics/break1_error.spl");
                interpret("doc/examples/basics/continue0.spl");
                interpret("doc/examples/basics/continue1.spl");
                interpret("doc/examples/basics/continue1_error.spl");
                interpret("doc/examples/basics/group0.spl");
                interpret("doc/examples/basics/fst0.spl");
                interpret("doc/examples/basics/fst0_error.spl");
                interpret("doc/examples/basics/snd0.spl");
                interpret("doc/examples/basics/snd0_error.spl");
                interpret("doc/examples/basics/hd0.spl");
                interpret("doc/examples/basics/hd0_error.spl");
                interpret("doc/examples/basics/hd1_error.spl");
                interpret("doc/examples/basics/tl0.spl");
                interpret("doc/examples/basics/tl0_error.spl");
                interpret("doc/examples/basics/tl1_error.spl");
                interpret("doc/examples/basics/print0.spl");
                interpret("doc/examples/basics/println0.spl");
                interpret("doc/examples/basics/iszero0.spl");
                interpret("doc/examples/basics/iszero0_error.spl");
                interpret("doc/examples/basics/pred0.spl");
                interpret("doc/examples/basics/pred0_error.spl");
                interpret("doc/examples/basics/succ0.spl");
                interpret("doc/examples/basics/succ0_error.spl");

                interpret("doc/examples/advanced/let0_poly.spl");
                interpret("doc/examples/advanced/let1_poly.spl");
                interpret("doc/examples/advanced/let2_poly.spl");
                interpret("doc/examples/advanced/fst0_poly.spl");
                interpret("doc/examples/advanced/fst0_redefine.spl");
                interpret("doc/examples/advanced/fst1_redefine.spl");
                interpret("doc/examples/advanced/snd0_poly.spl");
                interpret("doc/examples/advanced/snd0_redefine.spl");
                interpret("doc/examples/advanced/snd1_redefine.spl");
                interpret("doc/examples/advanced/hd0_poly.spl");
                interpret("doc/examples/advanced/hd0_redefine.spl");
                interpret("doc/examples/advanced/hd1_redefine.spl");
                interpret("doc/examples/advanced/tl0_poly.spl");
                interpret("doc/examples/advanced/tl0_redefine.spl");
                interpret("doc/examples/advanced/tl1_redefine.spl");
                interpret("doc/examples/advanced/print0_poly.spl");
                interpret("doc/examples/advanced/print0_redefine.spl");
                interpret("doc/examples/advanced/print1_redefine.spl");
                interpret("doc/examples/advanced/println0_poly.spl");
                interpret("doc/examples/advanced/println0_redefine.spl");
                interpret("doc/examples/advanced/println1_redefine.spl");
                interpret("doc/examples/advanced/iszero0_redefine.spl");
                interpret("doc/examples/advanced/iszero1_redefine.spl");
                interpret("doc/examples/advanced/pred0_redefine.spl");
                interpret("doc/examples/advanced/pred1_redefine.spl");
                interpret("doc/examples/advanced/succ0_redefine.spl");
                interpret("doc/examples/advanced/succ1_redefine.spl");
                interpret("doc/examples/advanced/gc0.spl");

                interpret("doc/examples/commons/factorial1.spl");
                interpret("doc/examples/commons/factorial2.spl");
                interpret("doc/examples/commons/fibonacci.spl");
                interpret("doc/examples/commons/gcd1.spl");
                interpret("doc/examples/commons/gcd2.spl");
                interpret("doc/examples/commons/iseven.spl");
                interpret("doc/examples/commons/lists.spl");
                interpret("doc/examples/commons/listsum1.spl");
                interpret("doc/examples/commons/listsum2.spl");
                interpret("doc/examples/commons/map.spl");
                interpret("doc/examples/commons/minus.spl");
                interpret("doc/examples/commons/pairmax.spl");
                interpret("doc/examples/commons/plus.spl");
                interpret("doc/examples/commons/sum.spl");
                interpret("doc/examples/commons/true.spl");
                interpret("doc/examples/commons/twice.spl");
            } else {
                interpret(args[0]);
            }
        }
    }
}
