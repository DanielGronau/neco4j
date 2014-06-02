package org.neco4j.function;

import org.junit.Test;

import junit.framework.TestCase;

public class TriFunctionTest extends TestCase {
    @Test
    public void testAndThen() throws Exception {
        TriFunction<String, String, String, String> fn = (t, u, v) -> t + u + v;
        assertEquals("abc", fn.apply("a", "b", "c"));
        TriFunction<String, String, String, String> fnAndThen = fn.andThen(s -> s + "x");
        assertEquals("abcx", fnAndThen.apply("a", "b", "c"));
    }
}