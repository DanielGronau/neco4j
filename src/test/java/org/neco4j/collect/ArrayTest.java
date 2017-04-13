package org.neco4j.collect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayTest {
    @Test
    public void addOpt() {
        assertThat(Array.<String>of().addOpt(0, "foo").getOrFail()).containsExactly("foo");
        assertThat(Array.<String>of().addOpt(-1, "foo").isEmpty()).isTrue();
        assertThat(Array.<String>of().addOpt(1, "foo").isEmpty()).isTrue();

        assertThat(Array.of("bar", "baz").addOpt(-1, "foo").isEmpty()).isTrue();
        assertThat(Array.of("bar", "baz").addOpt(0, "foo").getOrFail()).containsExactly("foo", "bar", "baz");
        assertThat(Array.of("bar", "baz").addOpt(1, "foo").getOrFail()).containsExactly("bar", "foo", "baz");
        assertThat(Array.of("bar", "baz").addOpt(2, "foo").getOrFail()).containsExactly("bar", "baz", "foo");
        assertThat(Array.of("bar", "baz").addOpt(3, "foo").isEmpty()).isTrue();
    }

    @Test
    public void getOpt() {
        assertThat(Array.<String>of().getOpt(-1).isEmpty()).isTrue();
        assertThat(Array.<String>of().getOpt(0).isEmpty()).isTrue();
        assertThat(Array.<String>of().getOpt(1).isEmpty()).isTrue();

        assertThat(Array.of("foo", "bar").getOpt(-1).isEmpty()).isTrue();
        assertThat(Array.of("foo", "bar").getOpt(0).getOrFail()).isEqualTo("foo");
        assertThat(Array.of("foo", "bar").getOpt(1).getOrFail()).isEqualTo("bar");
        assertThat(Array.of("foo", "bar").getOpt(2).isEmpty()).isTrue();
    }

    @Test
    public void removeOpt() {
        assertThat(Array.<String>of().removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.<String>of().removeOpt(0).isEmpty()).isTrue();
        assertThat(Array.<String>of().removeOpt(1).isEmpty()).isTrue();

        assertThat(Array.of("foo").removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.of("foo").removeOpt(0).getOrFail()).isEmpty();
        assertThat(Array.of("foo").removeOpt(1).isEmpty()).isTrue();

        assertThat(Array.of("foo", "bar").removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.of("foo", "bar").removeOpt(0).getOrFail()).containsExactly("bar");
        assertThat(Array.of("foo", "bar").removeOpt(1).getOrFail()).containsExactly("foo");
        assertThat(Array.of("foo", "bar").removeOpt(2).isEmpty()).isTrue();
    }

    @Test
    public void size() {
        assertThat(Array.<String>of().size()).isEqualTo(0L);
        assertThat(Array.of("foo", "bar", "baz").size()).isEqualTo(3L);
    }

    @Test
    public void toString_() {
        assertThat(Array.<String>of().toString()).isEqualTo("Array[]");
        assertThat(Array.of("foo", "bar", "baz").toString()).isEqualTo("Array[foo, bar, baz]");
    }

    @Test
    public void toList() {
        assertThat(Array.<String>of().toList()).isEmpty();
        assertThat(Array.of("foo", "bar", "baz").toList()).containsExactly("foo", "bar", "baz");
    }

    @Test
    public void addOptInts() {
        assertThat(Array.ints().addOpt(0, 42).getOrFail()).containsExactly(42);
        assertThat(Array.ints().addOpt(-1, 42).isEmpty()).isTrue();
        assertThat(Array.ints().addOpt(1, 42).isEmpty()).isTrue();

        assertThat(Array.ints(47, 11).addOpt(-1, 42).isEmpty()).isTrue();
        assertThat(Array.ints(47, 11).addOpt(0, 42).getOrFail()).containsExactly(42, 47, 11);
        assertThat(Array.ints(47, 11).addOpt(1, 42).getOrFail()).containsExactly(47, 42, 11);
        assertThat(Array.ints(47, 11).addOpt(2, 42).getOrFail()).containsExactly(47, 11, 42);
        assertThat(Array.ints(47, 11).addOpt(3, 42).isEmpty()).isTrue();
    }

    @Test
    public void getOptInts() {
        assertThat(Array.ints().getOpt(-1).isEmpty()).isTrue();
        assertThat(Array.ints().getOpt(0).isEmpty()).isTrue();
        assertThat(Array.ints().getOpt(1).isEmpty()).isTrue();

        assertThat(Array.ints(47, 11).getOpt(-1).isEmpty()).isTrue();
        assertThat(Array.ints(47, 11).getOpt(0).getOrFail()).isEqualTo(47);
        assertThat(Array.ints(47, 11).getOpt(1).getOrFail()).isEqualTo(11);
        assertThat(Array.ints(47, 11).getOpt(2).isEmpty()).isTrue();
    }

    @Test
    public void removeOptInts() {
        assertThat(Array.ints().removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.ints().removeOpt(0).isEmpty()).isTrue();
        assertThat(Array.ints().removeOpt(1).isEmpty()).isTrue();

        assertThat(Array.ints(42).removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.ints(42).removeOpt(0).getOrFail()).isEmpty();
        assertThat(Array.ints(42).removeOpt(1).isEmpty()).isTrue();

        assertThat(Array.ints(47, 11).removeOpt(-1).isEmpty()).isTrue();
        assertThat(Array.ints(47, 11).removeOpt(0).getOrFail()).containsExactly(11);
        assertThat(Array.ints(47, 11).removeOpt(1).getOrFail()).containsExactly(47);
        assertThat(Array.ints(47, 11).removeOpt(2).isEmpty()).isTrue();
    }

    @Test
    public void sizeInts() {
        assertThat(Array.ints().size()).isEqualTo(0L);
        assertThat(Array.ints(42, 47, 11).size()).isEqualTo(3L);
    }

    @Test
    public void toString_Ints() {
        assertThat(Array.ints().toString()).isEqualTo("Array[]");
        assertThat(Array.ints(42, 47, 11).toString()).isEqualTo("Array[42, 47, 11]");
    }

    @Test
    public void toListInts() {
        assertThat(Array.ints().toList()).isEmpty();
        assertThat(Array.ints(42, 47, 11).toList()).containsExactly(42, 47, 11);
    }

}