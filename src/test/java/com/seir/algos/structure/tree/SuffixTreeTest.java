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
        assertFalse(tree.search("nanna"));
    }
    @Test
    public void testSearch2() {
        SuffixTree tree = new SuffixTree("palmeiras");
        assertTrue(tree.search("ras"));
        assertFalse(tree.search("pal"));
        assertTrue(tree.search("almeiras"));
        assertTrue(tree.search("meiras"));
        assertTrue(tree.search("meir"));
        assertTrue(tree.search("me"));
        assertTrue(tree.search("mei"));
        assertTrue(tree.search("meir"));
        assertTrue(tree.search("meiras"));
    }
}
