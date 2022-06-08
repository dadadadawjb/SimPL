package simpl.typing;

import java.util.Set;
import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        // add pre-defined function types
        /**
         * // not support polymorphism
         * TypeVar tv1 = new TypeVar(true);
         * TypeVar tv2 = new TypeVar(true);
         * E = TypeEnv.of(empty, Symbol.symbol("fst"), new ArrowType(new PairType(tv1, tv2), tv1));
         * TypeVar tv3 = new TypeVar(true);
         * TypeVar tv4 = new TypeVar(true);
         * E = TypeEnv.of(E, Symbol.symbol("snd"), new ArrowType(new PairType(tv3, tv4), tv4));
         * TypeVar tv5 = new TypeVar(true);
         * E = TypeEnv.of(E, Symbol.symbol("hd"), new ArrowType(new ListType(tv5), tv5));
         * TypeVar tv6 = new TypeVar(true);
         * E = TypeEnv.of(E, Symbol.symbol("tl"), new ArrowType(new ListType(tv6), new ListType(tv6)));
         */

        // generic programming library, polymorphism
        TypeVar tv1 = new TypeVar(true);
        TypeVar tv2 = new TypeVar(true);
        E = TypeEnv.of(empty, Symbol.symbol("fst"), new PolyType(new ArrowType(new PairType(tv1, tv2), tv1)));
        TypeVar tv3 = new TypeVar(true);
        TypeVar tv4 = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("snd"), new PolyType(new ArrowType(new PairType(tv3, tv4), tv4)));
        TypeVar tv5 = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("hd"), new PolyType(new ArrowType(new ListType(tv5), tv5)));
        TypeVar tv6 = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("tl"), new PolyType(new ArrowType(new ListType(tv6), new ListType(tv6))));

        TypeVar tv7 = new TypeVar(true);
		E = TypeEnv.of(E, Symbol.symbol("print"), new PolyType(new ArrowType(tv7, Type.UNIT)));
        TypeVar tv8 = new TypeVar(true);
		E = TypeEnv.of(E, Symbol.symbol("println"), new PolyType(new ArrowType(tv8, Type.UNIT)));

        E = TypeEnv.of(E, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }

    @Override
    public Set<TypeVar> allTypeVars() {
        return this.E.allTypeVars();
    }
}
