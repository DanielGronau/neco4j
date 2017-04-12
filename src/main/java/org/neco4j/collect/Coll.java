package org.neco4j.collect;

import java.util.NoSuchElementException;

/**
 * A general-purpose immutable collection.
 * @param <K> the key or index type for accessing elements
 * @param <V> the element type
 * @param <C> the collection self-type
 */
public interface Coll<K, V, C extends Coll<K, V, C>> {

    /**
     * Adds an entry to the collection, if possible
     * @param k the key or index
     * @param v the value
     * @return if successful, an enlarged collection wrapped in an Opt, else Opt.none
     */
    Opt<C> addOpt(K k, V v);

    /**
     * Retrieves an element of the collection, if possible
     * @param k the key or index
     * @return if successful, the element wrapped in an Opt, else Opt.none
     */
    Opt<V> getOpt(K k);

    /**
     * Retrieves an element of the collection, or throws an exception if the collection is empty.
     * @param k the key or index
     * @return the element, if successful
     * @throws NoSuchElementException  if empty
     */
    default V getOrFail(K k) throws NoSuchElementException {
        return getOpt(k).getOrFail();
    }

    /**
     * Retrieves an element of the collection, or returns a given default value
     * @param k the key or index
     * @param defaultValue the defaultValue
     * @return the element, or the default value if empty
     */
    default V getOrElse(K k, V defaultValue) {
        return getOpt(k).getOrElse(defaultValue);
    }

    /**
     * Removes an element from the collection, if possible
     * @param k the key or index
     * @return if successful, an narrowed collection wrapped in an Opt, else Opt.none
     */
    Opt<C> removeOpt(K k);

    /**
     * Checks whether a collection is empty.
     * @return true if empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of the collection, or Long.MX_VALUE for infinite collections
     * @return the size
     */
    long size();

    /**
     * Returns the collection casted to its self-type
     * @return the collection
     */
    @SuppressWarnings("unchecked")
    default C self() {
        return (C) this;
    }
}