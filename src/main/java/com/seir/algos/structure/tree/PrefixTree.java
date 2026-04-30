package com.seir.algos.structure.tree;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrefixTree {

    public static final char WORD_START = '$';

    @Getter
    public static class Node {
        @Getter
        private char value;
        private final Map<Character, Node> children;
        @Getter
        private boolean isWord;

        Node(char value, Map<Character, Node> children, boolean isWord) {
            this.value = value;
            this.children = children;
            this.isWord = isWord;
        }
        public Node(char value) {
            this(value, new HashMap<>(), false);
        }
        public boolean hasChild(char value) {
            return children.containsKey(value);
        }
        public Optional<Node> getChild(char value) {
            return Optional.ofNullable(children.get(value));
        }
        public Node addChild(char value) {
            return hasChild(value) ? getChild(value).orElse(children.put(value, new Node(value));
        }
    }

    private final Node root;
    public PrefixTree() {
        this.root = new Node(WORD_START);
    }
    public void insert(String word) {
        Node currentNode = root;
        for (char c : word.toCharArray()) {
            if (!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new Node(c));
            }
            currentNode = currentNode.children.get(c);
        }
        currentNode.isWord = true;
    }

}
