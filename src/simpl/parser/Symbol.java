package simpl.parser;

import java.util.Map;
import java.util.HashMap;

public class Symbol {

    private String name;

    private Symbol(String n) {
        name = n;
    }

    public String toString() {
        return name;
    }

    private static int vcnt = 0;            // actual variable must > 0
    private static int underline = 0;       // 0 determines have not new symbol, if new then must > 0

    private static HashMap<String, Symbol> dict = new HashMap<String, Symbol>();

    /**
     * Make return the unique symbol associated with a string. Repeated calls to <tt>symbol("abc")</tt> will return the
     * same Symbol.
     */
    public static Symbol symbol(String n) {
        String u = n.intern();
        Symbol s = dict.get(u);
        if (s == null) {
            s = new Symbol(u);
            dict.put(u, s);
        }
        return s;
    }

    // symbol equal means string value equal
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Symbol)
            return name.equals(((Symbol) obj).name);
        else
            return false;
    }

    // generate a new symbol like "_v1"
    public static Symbol newSymbol() {
        if (underline == 0) {
            // the first time to call newSymbol(), then we should first determine the pre-underline
            String prefix = "v";
            boolean flag1 = false;          // determine whether we find a unique prefix
            while (!flag1) {
                prefix = "_" + prefix;
                ++underline;
                boolean flag2 = true;       // determine whether there does not exist the prefix
                for (Map.Entry<String, Symbol> entry : dict.entrySet()) {
                    if (entry.getValue().name.startsWith(prefix)) {
                        flag2 = false;
                        break;
                    }
                }
                if (flag2)
                    flag1 = true;
                else
                    flag1 = false;
            }

            // create the new symbol
            Symbol result = Symbol.symbol(prefix + ++vcnt);
            return result;
        } else {
            // create the new symbol
            String prefix = "v";
            for (int i = 0; i < underline; ++i)
                prefix = "_" + prefix;
            Symbol result = Symbol.symbol(prefix + ++vcnt);
            return result;
        }
    }
}
