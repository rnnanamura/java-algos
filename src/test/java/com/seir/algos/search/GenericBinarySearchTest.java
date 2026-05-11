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
}
