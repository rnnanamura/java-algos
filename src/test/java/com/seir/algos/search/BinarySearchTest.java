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

    @Test
    public void testMinCutHeight() {
        List<Integer> heights = List.of(1, 2, 3, 4, 5);
        int targetCumulation = 5;
        int minHeight = BinarySearch.minCutHeight(heights, targetCumulation);
        assertEquals(2, minHeight);
    }
    @Test
    public void testMinCutHeight2() {
        List<Integer> heights = List.of(2, 6, 3, 8);
        int targetCumulation = 7;
        int minHeight = BinarySearch.minCutHeight(heights, targetCumulation);
        assertEquals(3, minHeight);
    }

    @Test
    public void testSearchInRotatedSortedArray() {
        int index = BinarySearch.searchInRotatedSortedArray(List.of(4, 5, 6, 7, 0, 1, 2), 4);
        assertEquals(0, index);
        index = BinarySearch.searchInRotatedSortedArray( List.of(4, 5, 6, 7, 0, 1, 2), 0);
        assertEquals(4, index);
        index = BinarySearch.searchInRotatedSortedArray(List.of(4, 5, 6, 7, 0, 1, 2), 3);
        assertEquals(-1, index);
    }

}
