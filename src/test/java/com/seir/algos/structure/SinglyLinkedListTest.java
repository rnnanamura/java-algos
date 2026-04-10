package com.seir.algos.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SinglyLinkedListTest {
    @Test
    public void testAdd() {
        SinglyLinkedList list = new SinglyLinkedList(1);
        list.add(2);
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertNull(list.getTail().getNext());

        assertEquals(1, list.getHead().getData());
        assertEquals(2, list.getTail().getData());

        list.addToHead(0);
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertEquals(0, list.getHead().getData());
        assertEquals(1, list.getHead().getNext().getData());
        assertEquals(2, list.getTail().getData());
    }
    @Test
    public void testCyclid() {
        SinglyLinkedList list = new SinglyLinkedList(1);
        list.add(2);
        list.add(3);
        list.add(-1);
        assertFalse(list.isCyclic());
    }
    @Test
    public void testCyclid2() {
        SinglyLinkedList list = new SinglyLinkedList(0);
        list.add(1);
        SinglyLinkedList.SinglyLinkedListNode node2 = list.add(2);
        list.add(3);
        list.add(4);
        SinglyLinkedList.SinglyLinkedListNode node5 = list.add(5);
        node5.next = node2;
        assertTrue(list.isCyclic());
    }
    @Test
    public void testCyclid3() {
        SinglyLinkedList list = new SinglyLinkedList(0);
        list.add(1);
        SinglyLinkedList.SinglyLinkedListNode node2 = list.add(2);
        list.add(3);
        list.add(4);
        SinglyLinkedList.SinglyLinkedListNode node5 = list.add(5);
        assertFalse(list.isCyclic());
    }
}
