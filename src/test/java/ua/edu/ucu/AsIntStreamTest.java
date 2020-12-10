package ua.edu.ucu;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

public class AsIntStreamTest {
    private IntStream commonStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        commonStream = AsIntStream.of(-1, 0, 1, 2, 3);
        emptyStream = AsIntStream.of();
    }

    @Test
    public void testCommonAverage() {
        double expResult = commonStream.average();
        double result = 1.0;
        assertEquals(expResult, result, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage() {
        double expResult = emptyStream.average();
    }

    @Test
    public void testCommonMax() {
        int expResult = commonStream.max();
        int result = 3;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax() {
        int expResult = emptyStream.max();
    }

    @Test
    public void testCommonMin() {
        int expResult = commonStream.min();
        int result = -1;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin() {
        int expResult = emptyStream.min();
    }

    @Test
    public void testCommonCount() {
        long expResult = commonStream.count();
        long result = 5;
        assertEquals(expResult, result);
    }

    @Test
    public void testEmptyCount() {
        long expResult = emptyStream.count();
        long result = 0;
        assertEquals(expResult, result);
    }

    @Test
    public void testCommonSum() {
        int expResult = commonStream.sum();
        int result = 5;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        int expResult = emptyStream.sum();
    }

    @Test
    public void testCommonToArray() {
        int[] expResult = commonStream.toArray();
        int[] result = {-1, 0, 1, 2, 3};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testEmptyToArray() {
        int[] expResult = emptyStream.toArray();
        int[] result = {};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testCommonFilter() {
        int[] expResult = commonStream.filter(x -> x > 0).toArray();
        int[] result = {1, 2, 3};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testEmptyFilter() {
        int[] expResult = emptyStream.filter(x -> x > 0).toArray();
        int[] result = {};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testCommonForEach() {
        StringBuilder str = new StringBuilder();
        commonStream.forEach(str::append);
        String result = str.toString();
        String expResult = "-10123";
        assertEquals(expResult, result);
    }

    @Test
    public void testEmptyForEach() {
        StringBuilder str = new StringBuilder();
        emptyStream.forEach(str::append);
        String result = str.toString();
        String expResult = "";
        assertEquals(expResult, result);
    }

    @Test
    public void testCommonMap() {
        int[] expResult = commonStream.map(x -> x * x).toArray();
        int[] result = {1, 0, 1, 4, 9};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testEmptyMap() {
        int[] expResult = emptyStream.map(x -> x * x).toArray();
        int[] result = {};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testCommonFlatMap() {
        int[] expResult = commonStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        int[] result = {-2, -1, 0, -1, 0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testEmptyFlatMap() {
        int[] expResult = emptyStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        int[] result = {};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testCommonReduce() {
        int expResult = commonStream.reduce(10, (sum, x) -> sum += x);
        int result = 15;
        assertEquals(expResult, result);
    }

    @Test
    public void testEmptyReduce() {
        int expResult = emptyStream.reduce(10, (sum, x) -> sum += x);
        int result = 10;
        assertEquals(expResult, result);
    }
}
