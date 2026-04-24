package com.seir.algos.pointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWindowTest {

    @Test
    public void testAnagram() {
        assertEquals(3,  SlidingWindow.numberOfAnagrams("cba", "abcdabcaaaa"));
    }
}
