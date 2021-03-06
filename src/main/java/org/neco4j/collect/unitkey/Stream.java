package org.neco4j.collect.unitkey;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Stream<V> implements UnitKeyInfinite<V, Stream<V>>,
                                      UnitKeyAddable<V, Stream<V>>,
                                      UnitKeyPuttable<V, Stream<V>> {

    private final V _value;
    private final Supplier<Stream<V>> _next;

    private Stream(V value, Supplier<Stream<V>> next) {
        _value = value;
        _next = next;
    }

    @Override
    public Stream<V> add(V v) {
        return new Stream<>(v, () -> this);
    }

    @Override
    public Stream<V> put(V v) {
        return new Stream<>(v, _next::get);
    }

    @Override
    public V get() {
        return _value;
    }

    @Override
    public Stream<V> remove() {
        return _next.get();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (V v : this) {
            sb.append(sb.length() == 0 ? "" : ", ").append(v);
            if (i++ == 9) {
                break;
            }
        }
        return String.format("Stream[%s...]", sb);
    }

    public static <V> Stream<V> constant(V v) {
        return new Stream<>(v, () -> constant(v));
    }

    public static <V> Stream<V> iterate(V v, UnaryOperator<V> operator) {
        return new Stream<>(v, () -> iterate(operator.apply(v), operator));
    }

    public <W> Stream<W> map(Function<? super V, ? extends W> fn) {
        return new Stream<>(fn.apply(_value), () -> _next.get().map(fn));
    }

    // ATTENTION: This method assumes that the suppliers used for the Stream are idempotent
    public Stack<V> take(int n) {
        Stream<V> current = this;
        Stack<V>  result = Stack.empty();
        while(n-- > 0) {
           result = result.add(current._value);
           current = current._next.get();
        }
        return result.reverse();
    }
}
