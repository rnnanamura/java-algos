package com.seir.algos.search;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @Test
    public void testSearch() {
        int foundValue = BinarySearch.searchString("a", null);
        assertEquals(BinarySearch.NOT_FOUND, foundValue);
    }

    @Test
    public void testSearch2() {
        int valueIndex = BinarySearch.searchString("a", List.of("a", "b", "c"));
        assertEquals(0, valueIndex);
    }

    @Test
    public void testSearchOrInsertionPoint() {
        int insertionPoint = BinarySearch.searchOrInsertionString("d", List.of("a", "b", "c"));
        assertEquals(3, insertionPoint);

        insertionPoint = BinarySearch.searchOrInsertionString("a", List.of("a", "b", "c"));
        assertEquals(0, insertionPoint);
    }

    @Test
    public void testLowerBoundSearch() {
        int lowerBound = BinarySearch.lowerBoundSearch("b", List.of("a", "b", "b", "c"));
        assertEquals(1, lowerBound);
        lowerBound = BinarySearch.lowerBoundSearch("c", List.of("a", "b", "b", "c"));
        assertEquals(3, lowerBound);
        lowerBound = BinarySearch.lowerBoundSearch("d", List.of("a", "b", "b", "c"));
        assertEquals(-1, lowerBound);

    }

    @Test
    public void testUpperBoundSearch() {
        int upperBound = BinarySearch.upperBoundSearch("b", List.of("a", "b", "b", "c"));
        assertEquals(2, upperBound);
        upperBound = BinarySearch.upperBoundSearch("c", List.of("a", "b", "b", "c"));
        assertEquals(3, upperBound);
        upperBound = BinarySearch.upperBoundSearch("d", List.of("a", "b", "b", "c"));
        assertEquals(-1, upperBound);

    }
}
