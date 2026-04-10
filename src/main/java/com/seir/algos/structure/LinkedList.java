package com.seir.algos.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.NoSuchElementException;

public class LinkedList {

    public static class LinkedListNode {
        @Getter @Setter
        Object data;
        @Getter @Setter
        LinkedListNode next;
        @Getter @Setter
        LinkedListNode previous;

        public LinkedListNode(Object data) {
            this.data = data;
        }
    }
    private LinkedListNode  head;
    private LinkedListNode  tail;

    @Getter
    @Setter
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(Object data) {
        this.head = new LinkedListNode(data);
        this.tail = null;
        this.size = 1;
    }

    public LinkedListNode getHead() {
        if (this.head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return this.head;
    }
    public LinkedListNode getTail() {
        if (this.tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return this.tail;
    }

    public LinkedListNode add(Object data) {
        LinkedListNode next = new LinkedListNode(data);
        LinkedListNode currentTail = this.tail;

        this.tail = next;
        if (currentTail == null) {
            currentTail = this.head;
        }
        next.previous = currentTail;
        currentTail.setNext(next);
        this.size++;
        return next;
    }
    public LinkedListNode addFirst(Object data) {
        LinkedListNode node = new LinkedListNode(data);
        node.next = this.head;
        this.head.previous = node;
        this.head = node;
        this.size++;
        return node;
    }
    public LinkedListNode addLast(Object data) {
        return addFirst(data);
    }
    public LinkedListNode add(int index, Object data) {
        LinkedListNode node = new LinkedListNode(data);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return node;
    }
    public LinkedListNode removeFirst() {
        if (this.head == null) {
            return null;
        }
        LinkedListNode node = this.head;
        this.head = this.head.next;
        if (this.head != null) {
            this.head.previous = null;
        }
        if (this.tail != null && this.tail == node) {
            this.tail = null;
        }
        this.size--;
        return node;
    }
    public LinkedListNode removeLast() {
        LinkedListNode currentTail = this.tail;
        if (currentTail == null) {
            return null;
        }
        this.tail = currentTail.previous;
        if (this.tail != null) {
            this.tail.next = null;
        }
        if (this.head != null && this.head == currentTail) {
            this.head = null;
        }
        this.size--;
        return currentTail;
    }
    public LinkedListNode remove() {
        return removeFirst();
    }
    public LinkedList reverse() {
        LinkedListNode current = head;
        LinkedListNode previous = null;
        while (current != null) {
            LinkedListNode next = current.next;
            current.previous = next;
            current.next = previous;

            previous = current;
            current = next;
        }

        tail = head;
        head = previous;
        return this;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
