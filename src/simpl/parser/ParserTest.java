package simpl.parser;

import java.io.FileInputStream;
import java.io.InputStream;

public class ParserTest {

    private static void parse(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            System.out.println(filename);
            System.out.println(parseTree.value);
        }
        catch (Exception e) {
            System.out.println("syntax error");
        }
    }

    public static void main(String[] argv) {
        parse("doc/examples/basics/name0_error.spl");
        parse("doc/examples/basics/app0.spl");
        parse("doc/examples/basics/app0_error.spl");
        parse("doc/examples/basics/fn0.spl");
        parse("doc/examples/basics/fn1.spl");
        parse("doc/examples/basics/fn0_error.spl");
        parse("doc/examples/basics/cond0.spl");
        parse("doc/examples/basics/cond0_error.spl");
        parse("doc/examples/basics/and0.spl");
        parse("doc/examples/basics/and1.spl");
        parse("doc/examples/basics/and2.spl");
        parse("doc/examples/basics/and0_error.spl");
        parse("doc/examples/basics/or0.spl");
        parse("doc/examples/basics/or1.spl");
        parse("doc/examples/basics/or2.spl");
        parse("doc/examples/basics/or0_error.spl");
        parse("doc/examples/basics/not0.spl");
        parse("doc/examples/basics/not0_error.spl");
        parse("doc/examples/basics/add0.spl");
        parse("doc/examples/basics/add0_error.spl");
        parse("doc/examples/basics/sub0.spl");
        parse("doc/examples/basics/sub0_error.spl");
        parse("doc/examples/basics/mul0.spl");
        parse("doc/examples/basics/mul0_error.spl");
        parse("doc/examples/basics/div0.spl");
        parse("doc/examples/basics/div0_error.spl");
        parse("doc/examples/basics/div1_error.spl");
        parse("doc/examples/basics/mod0.spl");
        parse("doc/examples/basics/mod0_error.spl");
        parse("doc/examples/basics/mod1_error.spl");
        parse("doc/examples/basics/neg0.spl");
        parse("doc/examples/basics/neg0_error.spl");
        parse("doc/examples/basics/eq0.spl");
        parse("doc/examples/basics/eq1.spl");
        parse("doc/examples/basics/eq2.spl");
        parse("doc/examples/basics/eq3.spl");
        parse("doc/examples/basics/eq0_error.spl");
        parse("doc/examples/basics/eq4_error.spl");
        parse("doc/examples/basics/eq5_error.spl");
        parse("doc/examples/basics/neq0.spl");
        parse("doc/examples/basics/neq1.spl");
        parse("doc/examples/basics/neq2.spl");
        parse("doc/examples/basics/neq3.spl");
        parse("doc/examples/basics/neq0_error.spl");
        parse("doc/examples/basics/neq4_error.spl");
        parse("doc/examples/basics/neq5_error.spl");
        parse("doc/examples/basics/less0.spl");
        parse("doc/examples/basics/less1.spl");
        parse("doc/examples/basics/less0_error.spl");
        parse("doc/examples/basics/less1_error.spl");
        parse("doc/examples/basics/lesseq0.spl");
        parse("doc/examples/basics/lesseq1.spl");
        parse("doc/examples/basics/lesseq0_error.spl");
        parse("doc/examples/basics/lesseq1_error.spl");
        parse("doc/examples/basics/greater0.spl");
        parse("doc/examples/basics/greater1.spl");
        parse("doc/examples/basics/greater0_error.spl");
        parse("doc/examples/basics/greater1_error.spl");
        parse("doc/examples/basics/greatereq0.spl");
        parse("doc/examples/basics/greatereq1.spl");
        parse("doc/examples/basics/greatereq0_error.spl");
        parse("doc/examples/basics/greatereq1_error.spl");
        parse("doc/examples/basics/let0.spl");
        parse("doc/examples/basics/let1.spl");
        parse("doc/examples/basics/pair0.spl");
        parse("doc/examples/basics/pair1.spl");
        parse("doc/examples/basics/pair2.spl");
        parse("doc/examples/basics/pair3.spl");
        parse("doc/examples/basics/inl0.spl");
        parse("doc/examples/basics/inr0.spl");
        parse("doc/examples/basics/sumcase0.spl");
        parse("doc/examples/basics/sumcase1.spl");
        parse("doc/examples/basics/rec0.spl");
        parse("doc/examples/basics/nil0.spl");
        parse("doc/examples/basics/cons0.spl");
        parse("doc/examples/basics/cons0_error.spl");
        parse("doc/examples/basics/listcase0.spl");
        parse("doc/examples/basics/listcase1.spl");
        parse("doc/examples/basics/ref0.spl");
        parse("doc/examples/basics/ref1.spl");
        parse("doc/examples/basics/ref2.spl");
        parse("doc/examples/basics/deref0.spl");
        parse("doc/examples/basics/deref1.spl");
        parse("doc/examples/basics/deref0_error.spl");
        parse("doc/examples/basics/assign0.spl");
        parse("doc/examples/basics/assign0_error.spl");
        parse("doc/examples/basics/seq0.spl");
        parse("doc/examples/basics/seq0_error.spl");
        parse("doc/examples/basics/loop0.spl");
        parse("doc/examples/basics/break0.spl");
        parse("doc/examples/basics/break1.spl");
        parse("doc/examples/basics/break2.spl");
        parse("doc/examples/basics/break1_error.spl");
        parse("doc/examples/basics/continue0.spl");
        parse("doc/examples/basics/continue1.spl");
        parse("doc/examples/basics/continue1_error.spl");
        parse("doc/examples/basics/group0.spl");
        parse("doc/examples/basics/fst0.spl");
        parse("doc/examples/basics/fst0_error.spl");
        parse("doc/examples/basics/snd0.spl");
        parse("doc/examples/basics/snd0_error.spl");
        parse("doc/examples/basics/hd0.spl");
        parse("doc/examples/basics/hd0_error.spl");
        parse("doc/examples/basics/hd1_error.spl");
        parse("doc/examples/basics/tl0.spl");
        parse("doc/examples/basics/tl0_error.spl");
        parse("doc/examples/basics/tl1_error.spl");
        parse("doc/examples/basics/print0.spl");
        parse("doc/examples/basics/println0.spl");
        parse("doc/examples/basics/iszero0.spl");
        parse("doc/examples/basics/iszero0_error.spl");
        parse("doc/examples/basics/pred0.spl");
        parse("doc/examples/basics/pred0_error.spl");
        parse("doc/examples/basics/succ0.spl");
        parse("doc/examples/basics/succ0_error.spl");

        parse("doc/examples/advanced/let0_poly.spl");
        parse("doc/examples/advanced/let1_poly.spl");
        parse("doc/examples/advanced/let2_poly.spl");
        parse("doc/examples/advanced/fst0_poly.spl");
        parse("doc/examples/advanced/fst0_redefine.spl");
        parse("doc/examples/advanced/fst1_redefine.spl");
        parse("doc/examples/advanced/snd0_poly.spl");
        parse("doc/examples/advanced/snd0_redefine.spl");
        parse("doc/examples/advanced/snd1_redefine.spl");
        parse("doc/examples/advanced/hd0_poly.spl");
        parse("doc/examples/advanced/hd0_redefine.spl");
        parse("doc/examples/advanced/hd1_redefine.spl");
        parse("doc/examples/advanced/tl0_poly.spl");
        parse("doc/examples/advanced/tl0_redefine.spl");
        parse("doc/examples/advanced/tl1_redefine.spl");
        parse("doc/examples/advanced/print0_poly.spl");
        parse("doc/examples/advanced/print0_redefine.spl");
        parse("doc/examples/advanced/print1_redefine.spl");
        parse("doc/examples/advanced/println0_poly.spl");
        parse("doc/examples/advanced/println0_redefine.spl");
        parse("doc/examples/advanced/println1_redefine.spl");
        parse("doc/examples/advanced/iszero0_redefine.spl");
        parse("doc/examples/advanced/iszero1_redefine.spl");
        parse("doc/examples/advanced/pred0_redefine.spl");
        parse("doc/examples/advanced/pred1_redefine.spl");
        parse("doc/examples/advanced/succ0_redefine.spl");
        parse("doc/examples/advanced/succ1_redefine.spl");
        parse("doc/examples/advanced/gc0.spl");

        parse("doc/examples/commons/factorial1.spl");
        parse("doc/examples/commons/factorial2.spl");
        parse("doc/examples/commons/factorial3.spl");
        parse("doc/examples/commons/factorial4.spl");
        parse("doc/examples/commons/factorial5.spl");
        parse("doc/examples/commons/factorial6.spl");
        parse("doc/examples/commons/fibonacci.spl");
        parse("doc/examples/commons/gcd1.spl");
        parse("doc/examples/commons/gcd2.spl");
        parse("doc/examples/commons/iseven.spl");
        parse("doc/examples/commons/lists.spl");
        parse("doc/examples/commons/listsum1.spl");
        parse("doc/examples/commons/listsum2.spl");
        parse("doc/examples/commons/map.spl");
        parse("doc/examples/commons/minus.spl");
        parse("doc/examples/commons/pairmax.spl");
        parse("doc/examples/commons/plus.spl");
        parse("doc/examples/commons/sum.spl");
        parse("doc/examples/commons/true.spl");
        parse("doc/examples/commons/twice.spl");
    }
}
