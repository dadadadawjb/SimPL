package simpl.interpreter;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import simpl.parser.Symbol;

// mapping from memory pointers to values
public class Mem extends HashMap<Integer, Value> {

    private static final long serialVersionUID = -1155291135560730618L;

    private static final int HEAP_SIZE = 20;
    private static final int GC = 2;        // 0 for no garbage collection, 1 for reference counting, 2 for mark-and-sweep, 3 for copy collection
    private int in_use = 0;                 // only used in copy collection, 0 for [0, HEAP_SIZE/2-1] in use, 1 for [HEAP_SIZE/2, HEAP_SIZE-1] in use
    private Map<Integer, Integer> mapping_table = new HashMap<>();      // only used in copy collection, partial map from [0, HEAP_SIZE/2-1] to [0, HEAP_SIZE-1]
    private int next_pos = 0;               // only used in copy collection

    public Value read(int ptr) {
        switch (GC) {
            case 0: return get(ptr);
            case 1: return get(ptr);
            case 2: return get(ptr);
            case 3: return get(mapping_table.get(ptr));
            default: return get(ptr);                               // actually never reach here
        }
    }

    public void write(int ptr, Value val) {
        switch (GC) {
            case 0: put(ptr, val); break;
            case 1: put(ptr, val); break;
            case 2: put(ptr, val); break;
            case 3: put(mapping_table.get(ptr), val); break;
            default: put(ptr, val); break;                          // actually never reach here
        }
    }

    public int alloc(State s) throws RuntimeError {
        // memory address starts from 0 to HEAP_SIZE-1
        int ptr = s.p.get();
        switch (GC) {
            case 0: {
                // not garbage collection
                if (ptr == HEAP_SIZE)
                    throw new RuntimeError("heap overflow");
                s.p.set(ptr + 1);
                return ptr;
            }
            case 1: {
                // reference counting
                // hard to detect when the reference is built and when it dies
                throw new RuntimeError("reference counting has not implemented");
            }
            case 2: {
                // mark-and-sweep
                if (ptr == HEAP_SIZE) {
                    // trigger garbage collection
                    // mark
                    Set<Integer> marked = new HashSet<>();
                    Map<Symbol, RefValue> references = s.E.allReferences();
                    for (RefValue value : references.values())
                        marked.add(value.p);
                    
                    // sweep
                    if (marked.size() == HEAP_SIZE) {
                        // all referenced
                        throw new RuntimeError("heap overflow");
                    } else {
                        // find the first hole
                        int first = -1;
                        for (int i = 0; i < s.p.get(); ++i) {
                            if (!marked.contains(i)) {
                                first = i;
                                break;
                            }
                        }
                        if (first == -1)
                            throw new RuntimeError("something error happens in memory");    // actually never reach here
                        else
                            return first;
                    }
                } else {
                    // normal allocation
                    s.p.set(ptr + 1);
                    return ptr;
                }
            }
            case 3: {
                // copy collection
                if (ptr == HEAP_SIZE / 2) {
                    // trigger garbage collection
                    // collect
                    Set<Integer> reachable = new HashSet<>();
                    Map<Symbol, RefValue> references = s.E.allReferences();
                    for (RefValue value : references.values())
                        reachable.add(value.p);

                    // copy
                    int pos = (1 - in_use) * (HEAP_SIZE / 2);
                    for (int p = 0; p < HEAP_SIZE / 2; ++p) {
                        if (reachable.contains(p)) {
                            put(pos, read(p));
                            mapping_table.put(p, pos);
                            ++pos;
                        } else {
                            mapping_table.remove(p);
                        }
                    }

                    // swap
                    in_use = 1 - in_use;
                    for (int p = 0; p <= HEAP_SIZE / 2; ++p) {
                        if (!mapping_table.containsKey(p)) {
                            ptr = p;
                            break;
                        }
                    }
                    if (ptr == HEAP_SIZE / 2)
                        throw new RuntimeError("heap overflow"); 
                    mapping_table.put(ptr, pos);
                    next_pos = pos + 1;
                    for (int p = 0; p <= HEAP_SIZE / 2; ++p) {
                        if (!mapping_table.containsKey(p)) {
                            s.p.set(p);
                            break;
                        }
                    }
                    return ptr;
                } else {
                    // normal allocation
                    mapping_table.put(ptr, next_pos);
                    next_pos += 1;
                    for (int p = 0; p <= HEAP_SIZE / 2; ++p) {
                        if (!mapping_table.containsKey(p)) {
                            s.p.set(p);
                            break;
                        }
                    }
                    return ptr;
                }
            }
            default: throw new RuntimeError("Unsupported garbage collection");      // actually never reach here
        }
    }
}
