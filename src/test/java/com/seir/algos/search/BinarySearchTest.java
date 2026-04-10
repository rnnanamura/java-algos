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
}
