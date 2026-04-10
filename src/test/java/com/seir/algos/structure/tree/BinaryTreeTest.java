package com.seir.algos.structure.tree;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    @Test
    public void testAddString() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("a");
        tree.add("b");
        tree.add("c");
        assertEquals("a", tree.getRoot().getData());
        assertEquals("b", tree.getRoot().getRight().getData());
        assertEquals("c", tree.getRoot().getRight().getRight().getData());
        assertNull(tree.getRoot().getLeft());
    }
    @Test
    public void testAddString2() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("b");
        tree.add("a");
        tree.add("c");
        assertEquals("b", tree.getRoot().getData());
        assertEquals("c", tree.getRoot().getRight().getData());
        assertEquals("a", tree.getRoot().getLeft().getData());
        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());
        assertNull(tree.getRoot().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getRight());
    }
    @Test
    public void testAddString3() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("b");
        tree.add("a");
        tree.add("r");
        tree.add("c");
        assertEquals("b", tree.getRoot().getData());
        assertEquals("r", tree.getRoot().getRight().getData());
        assertEquals("a", tree.getRoot().getLeft().getData());
        assertNotNull(tree.getRoot().getRight().getLeft());
        assertEquals("c", tree.getRoot().getRight().getLeft().getData());
        assertNull(tree.getRoot().getRight().getRight());
        assertNull(tree.getRoot().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getRight());
        assertTrue(tree.isTreeBalanced());
    }

    @Test
    public void testIsBalanced() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("b");
        tree.add("a");
        tree.add("r");
        tree.add("c");
        assertEquals("c", tree.getRoot().getRight().getLeft().getData());
        assertTrue(tree.isTreeBalanced());
        tree.add("d");
        assertEquals("d", tree.getRoot().getRight().getLeft().getRight().getData());
        assertFalse(tree.isTreeBalanced());
    }
    @Test
    public void testMaxDepth1() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("b");
        assertEquals(1, tree.getHeight());
        tree.add("a");
        assertEquals(2, tree.getHeight());
        tree.add("c");
        assertEquals(2, tree.getHeight());
    }
    @Test
    public void testMaxDepth2() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("a");
        assertEquals(1, tree.getHeight());
        tree.add("b");
        assertEquals(2, tree.getHeight());
        tree.add("c");
        assertEquals(3, tree.getHeight());
    }

    @Test
    public void testDepthFirstTraversal() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("c");
        assertEquals(1, tree.getSize());
        tree.add("b");
        assertEquals(2, tree.getSize());
        tree.add("a");
        assertEquals(3, tree.getSize());
        tree.add("d");
        assertEquals(4, tree.getSize());
        List<String> result = tree.depthFirstTraversal();
        assertEquals(4, result.size());
        assertEquals(List.of("c", "b", "a", "d"), result);
    }

    @Test
    public void testBreadthFirstTraversal() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("c");
        tree.add("b");
        tree.add("a");
        tree.add("d");
        List<String> result = tree.breadthFirstTraversal();
        assertEquals(4, result.size());
        assertEquals(List.of("c", "b", "d", "a"), result);
    }

    @Test
    public void testStringCharsBinaryTree() {
        BinaryTree<String> tree = new BinaryTree<>();
        String testString = "Abracadabra";
        for (char c: testString.toCharArray()) {
            tree.add(String.valueOf(c).toLowerCase());
        }
        assertEquals(List.of("a", "b", "r", "c", "d"), tree.breadthFirstTraversal());
    }

    @Test
    public void testSearch() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.add("c");
        tree.add("b");
        tree.add("a");
        tree.add("r");
        tree.add("d");

        Optional<BinaryTree.TreeNode<String>> result = tree.search("d");
        assertTrue(result.isPresent());
        assertEquals("d", result.get().getData());
        result = tree.search("e");
        assertFalse(result.isPresent());

    }

    @Test
    public void testMinMax() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(8);
        assertEquals(8, tree.max().orElseThrow());
        assertEquals(1, tree.min().orElseThrow());
    }
}
