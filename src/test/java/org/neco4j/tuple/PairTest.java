package org.neco4j.tuple;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PairTest {

    @Test
    public void testGet1() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        assertEquals("foo", pair.get1());
    }

    @Test
    public void testGet2() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        assertEquals(Integer.valueOf(42), pair.get2());
    }

    @Test
    public void testWith1() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<Character, Integer> pairWith = pair.with1('c');
        assertEquals(Character.valueOf('c'), pairWith.get1());
        assertEquals(Integer.valueOf(42), pairWith.get2());
    }

    @Test
    public void testWith2() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<String, Character> pairWith = pair.with2('c');
        assertEquals("foo", pairWith.get1());
        assertEquals(Character.valueOf('c'), pairWith.get2());
    }

    @Test
    public void testMap1() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<Integer, Integer> pairMap = pair.map1(s -> s.length());
        assertEquals(Integer.valueOf(3), pairMap.get1());
        assertEquals(Integer.valueOf(42), pairMap.get2());
    }

    @Test
    public void testMap2() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<String, String> pairMap = pair.map2(n -> "number " + n);
        assertEquals("foo", pairMap.get1());
        assertEquals("number 42", pairMap.get2());
    }

    @Test
    public void testBimap() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<Integer, String> pairMap = pair.bimap(s -> s.length(), n -> "number " + n);
        assertEquals(Integer.valueOf(3), pairMap.get1());
        assertEquals("number 42", pairMap.get2());
    }

    @Test
    public void testCollapse() throws Exception {
        Pair<String, Integer> pair = Pair.of("foobar", 3);
        String collapsed = pair.collapse((s,n) -> s.substring(n));
        assertEquals("bar", collapsed);
    }

    @Test
    public void testSwap() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        Pair<Integer, String> pairSwap = pair.swap();
        assertEquals(Integer.valueOf(42), pairSwap.get1());
        assertEquals("foo", pairSwap.get2());
    }

    @Test
    public void testToString() throws Exception {
        Pair<String, Integer> pair = Pair.of("foo", 42);
        assertEquals("(foo,42)", pair.toString());
    }

    @Test
    public void testHashCode() throws Exception {
        Pair<String, Integer> pair1 = Pair.of("foo", 4711);
        Pair<String, Integer> pair2 = Pair.of("foo", 4711);
        assertTrue(pair1.hashCode() == pair2.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Pair<String, Integer> pair1 = Pair.of("foo", 4711);
        Pair<String, Integer> pair2 = Pair.of("foo", 4711);
        Pair<String, Integer> pair3 = Pair.of("bar", 4711);
        Pair<String, Integer> pair4 = Pair.of("foo", 42);
        assertTrue(pair1.equals(pair2));
        assertFalse(pair1.equals(pair3));
        assertFalse(pair1.equals(pair4));
        assertFalse(pair3.equals(pair4));
    }
}