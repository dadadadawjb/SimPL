package simpl.interpreter;

import static simpl.parser.Symbol.symbol;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        // TODO
        // add pre-defined functions names
        Env env = new Env(E, symbol("fst"), new fst());
        env = new Env(env, symbol("snd"), new snd());
        env = new Env(env, symbol("hd"), new hd());
        env = new Env(env, symbol("tl"), new tl());
        env = new Env(env, symbol("iszero"), new iszero());
        env = new Env(env, symbol("pred"), new pred());
        env = new Env(env, symbol("succ"), new succ());
        return env;
    }
}
