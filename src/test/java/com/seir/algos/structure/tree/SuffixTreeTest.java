package com.seir.algos.structure.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuffixTreeTest {

    @Test
    public void testSearch() {
        SuffixTree tree = new SuffixTree("banana");
        assertTrue(tree.search("ana"));
        assertFalse(tree.search("banan"));
        assertTrue(tree.search("nana"));
    }
}
