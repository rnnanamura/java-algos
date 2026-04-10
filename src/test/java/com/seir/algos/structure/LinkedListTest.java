package com.seir.algos.structure;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    public void testAdd() {
        LinkedList list = new LinkedList(1);
        LinkedList.LinkedListNode next = list.add(2);
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertNull(list.getHead().getPrevious());
        assertNull(list.getTail().getNext());

        assertEquals(1, list.getHead().getData());
        assertNotNull(next);
        assertEquals(2, list.getTail().getData());
    }

    @Test
    public void testReverse() {
        LinkedList list = new LinkedList(1);
        LinkedList.LinkedListNode next = list.add(2);
        LinkedList reversed = list.reverse();
        assertEquals(2, reversed.getHead().getData());
        assertEquals(1, reversed.getTail().getData());
    }
    @Test
    public void testReverse2() {
        LinkedList list = new LinkedList(1);
        LinkedList.LinkedListNode next = list.add(2);
        list.add(3);
        LinkedList reversed = list.reverse();
        assertEquals(3, reversed.getHead().getData());
        assertEquals(2, reversed.getHead().getNext().getData());
        assertEquals(1, reversed.getTail().getData());
    }

    @Test
    public void testRemove() {
        LinkedList list = new LinkedList(1);
        LinkedList.LinkedListNode next = list.add(2);
        list.add(3);
        list.remove();
        assertEquals(2, list.getHead().getData());
        assertEquals(3, list.getTail().getData());
        list.removeLast();
        assertEquals(2, list.getHead().getData());
        assertEquals(2, list.getTail().getData());
        list.removeLast();
        assertThrows(NoSuchElementException.class,() -> {list.getTail(); });
        assertThrows(NoSuchElementException.class,() -> {list.getHead(); });

    }
}
