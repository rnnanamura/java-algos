package com.seir.algos.search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.seir.algos.search.GenericBinarySearch.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericBinarySearchTest {

    @Test
    public void testSearchString() {
        List<String> list = List.of("apple", "banana", "cherry", "date");
        String target = "cherry";
        int result =GenericBinarySearch.search(target, list);
        assertEquals(2, result);
    }
    @Test
    public void testSearchString2() {
        List<String> list = List.of("apple", "banana", "cherry", "date");
        String target = "cherries";
        int result =GenericBinarySearch.search(target, list);
        assertEquals(NOT_FOUND, result);
    }
    @Test
    public void testSearchOrInsertionPoint() {
        int insertionPoint = GenericBinarySearch.searchOrInsertion("d", List.of("a", "b", "c"));
        assertEquals(3, insertionPoint);

        insertionPoint = GenericBinarySearch.searchOrInsertion("a", List.of("a", "b", "c"));
        assertEquals(0, insertionPoint);
    }

    @Test
    public void testLowerBoundSearch() {
        int lowerBound = GenericBinarySearch.lowerBoundSearch("b", List.of("a", "b", "b", "c"));
        assertEquals(1, lowerBound);
        lowerBound = GenericBinarySearch.lowerBoundSearch("c", List.of("a", "b", "b", "c"));
        assertEquals(3, lowerBound);
        lowerBound = GenericBinarySearch.lowerBoundSearch("d", List.of("a", "b", "b", "c"));
        assertEquals(-1, lowerBound);

    }

    @Test
    public void testUpperBoundSearch() {
        int upperBound = GenericBinarySearch.upperBoundSearch("b", List.of("a", "b", "b", "c"));
        assertEquals(2, upperBound);
        upperBound = GenericBinarySearch.upperBoundSearch("c", List.of("a", "b", "b", "c"));
        assertEquals(3, upperBound);
        upperBound = GenericBinarySearch.upperBoundSearch("d", List.of("a", "b", "b", "c"));
        assertEquals(-1, upperBound);

    }

    @Test
    public void testMinCutHeight() {
        List<Integer> heights = List.of(1, 2, 3, 4, 5);
        int targetCumulation = 5;
        int minHeight = heights.stream().min(Integer::compareTo).orElse(0);
        int maxHeight = heights.stream().max(Integer::compareTo).orElse(1000);
        int height = GenericBinarySearch.minAccumulation(heights, targetCumulation, minHeight, maxHeight,
                GenericBinarySearchTest::cumulation);
        assertEquals(2, height);
    }
    @Test
    public void testMinCutHeight2() {
        List<Integer> heights = List.of(2, 6, 3, 8);
        int targetCumulation = 7;
        int minHeight = heights.stream().min(Integer::compareTo).orElse(0);
        int maxHeight = heights.stream().max(Integer::compareTo).orElse(1000);
        int height = GenericBinarySearch.minAccumulation(heights, targetCumulation, minHeight, maxHeight,
                GenericBinarySearchTest::cumulation);
        assertEquals(3, height);
    }


    private static int cumulation(List<Integer> heights, Integer height) {
        return heights.stream().map(h -> h > height ? h - height: 0).reduce(0, Integer::sum);
    }
    @Test
    public void testSearchInRotatedSortedArray() {
        int index = GenericBinarySearch.searchInRotatedSortedArray(List.of(4, 5, 6, 7, 0, 1, 2), 4);
        assertEquals(0, index);
        index = GenericBinarySearch.searchInRotatedSortedArray( List.of(4, 5, 6, 7, 0, 1, 2), 0);
        assertEquals(4, index);
        index = GenericBinarySearch.searchInRotatedSortedArray(List.of(4, 5, 6, 7, 0, 1, 2), 3);
        assertEquals(-1, index);
    }

}
