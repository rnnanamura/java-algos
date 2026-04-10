package com.seir.algos.search;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ComplementaryPairsTest {

    @Test
    public void testFindComplementPairs() {

        List<ComplementaryPairs.Pair> result = ComplementaryPairs.findComplementaryPairsInSortedList(null, 0);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        List<Integer> list = List.of(-2, -1, 1, 2, 3, 4, 5, 7, 8);
        result = ComplementaryPairs.findComplementaryPairsInSortedList(list, 10);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    public void testFindComplementPairs2() {

        Map<Integer, Integer> result = ComplementaryPairs.findComplementaryPairsInUnsortedList(null, 0);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        List<Integer> list = List.of(12, 11, -2, -1, 1, 2, 3, 4, 5, 7, 8);
        result = ComplementaryPairs.findComplementaryPairsInUnsortedList(list, 10);
        assertNotNull(result);
        assertEquals(4, result.size());

        list = List.of(12, 11, 7, 8, -2, -1, 1, 2, 3, 4, 5);
        result = ComplementaryPairs.findComplementaryPairsInUnsortedList(list, 10);
        assertNotNull(result);
        assertEquals(4, result.size());

        list = List.of(-1, 3, 4, 2);
        result = ComplementaryPairs.findComplementaryPairsInUnsortedList(list, 3);
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
