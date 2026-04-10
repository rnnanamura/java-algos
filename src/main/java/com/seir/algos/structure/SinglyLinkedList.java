package com.seir.algos.structure;

import lombok.Getter;

public class SinglyLinkedList {
    public static class SinglyLinkedListNode {
        @Getter
        Object data;
        @Getter
        SinglyLinkedListNode next;
        public SinglyLinkedListNode(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    @Getter
    private SinglyLinkedListNode head = null;
    @Getter
    private SinglyLinkedListNode tail = null;
    @Getter
    private int size=0;

    public SinglyLinkedList() {}
    public SinglyLinkedList(Object data) {
        this.head = new SinglyLinkedListNode(data);
        this.tail = this.head;
        this.size = 1;
    }
    public SinglyLinkedListNode addToHead(Object data) {

        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        if (this.head != null) {
            node.next = this.head;
        } else {
            this.tail = node;
        }
        this.head = node;
        this.size++;
        return node;
    }
    public SinglyLinkedListNode add(Object data) {
        if (this.head == null) {
            return addToHead(data);
        }
        this.tail.next = new SinglyLinkedListNode(data);
        this.tail = this.tail.next;
        return this.tail;
    }

    public boolean isCyclic() {
        if (this.head == null) {
            return false;
        }
        int slow = 0;
        int fast = 2;
        SinglyLinkedListNode currentNode = this.head;
        SinglyLinkedListNode fastNode = this.head;
        while (currentNode != null) {
            if (fastNode == null || fastNode.next == null) {
                fastNode = null;
            } else {
                fastNode = fastNode.next.next;
            }
            currentNode = currentNode.next;
            if (fastNode != null && currentNode == fastNode) {
                return true;
            }
        }
        return false;
    }
}
