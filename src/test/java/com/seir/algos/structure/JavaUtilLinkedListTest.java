package com.seir.algos.structure;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class JavaUtilLinkedListTest {

    @Test
    public void testRemove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeLast();
        assertEquals(1, list.getFirst().intValue());
        assertEquals(2, list.getLast().intValue());
        list.removeLast();
        assertEquals(1, list.getLast().intValue());
        list.removeLast();
        assertThrows(NoSuchElementException.class,() -> {list.getLast(); });
        assertThrows(NoSuchElementException.class,() -> {list.getFirst(); });
    }
}
