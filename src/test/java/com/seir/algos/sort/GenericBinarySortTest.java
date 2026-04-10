package com.seir.algos.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericBinarySortTest {
    @Test
    public void testSort1() {

        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        List<String> sortedList = GenericBinarySort.sort(list);
        assertEquals(list.size(), sortedList.size());

    }

    @Test
    public void testSortEmptyList() {

        List<String> list = new ArrayList<>();
        List<String> sortedList = GenericBinarySort.sort(list);
        assertEquals(list.size(), sortedList.size());

    }

    @Test
    public void testSortNullList() {

        List<String> list = null;
        List<String> sortedList = GenericBinarySort.sort(list);
        assertTrue(sortedList.isEmpty());

    }
}
