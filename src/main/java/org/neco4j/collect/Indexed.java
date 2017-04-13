package org.neco4j.collect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A random-access collection with successive integers (starting at 0) as keys.
 * @param <V>
 * @param <C>
 */
public interface Indexed<V, C extends Indexed<V,C>> extends Coll<Integer, V, C>, Iterable<V> {

    /**
     * Retrieves the first element of the indexed collection
     * @return the first element
     */
    default Opt<V> first() {
        return getOpt(0);
    }

    /**
     * Retrieves the last element of the indexed collection
     * @return the last element
     */
    default Opt<V> last() {
        return getOpt((int)size() - 1);
    }

    @Override
    default Iterator<V> iterator() {
        return new Iterator<V>() {

            private int _index = 0;

            @Override
            public boolean hasNext() {
                return _index < size();
            }

            @Override
            public V next() throws NoSuchElementException {
                if (hasNext()) {
                   return getOrFail(_index++);
                }
                throw new NoSuchElementException();
            }
        };
    }

    default List<V> toList() {
        List<V> result = new ArrayList<>((int)size());
        for (V v : this) {
            result.add(v);
        }
        return result;
    }
}
