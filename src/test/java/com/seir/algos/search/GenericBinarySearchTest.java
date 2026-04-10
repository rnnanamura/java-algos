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
}
