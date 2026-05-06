package com.seir.algos.structure.tree;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a suffix tree data structure for efficient string pattern matching.
 * Uses Ukkonen's algorithm.
 */
public class SuffixTree {
    public static class Node {
        @Getter
        int start;
        @Getter
        int end;
        @Getter
        Node suffixLink;
        @Getter
        Map<Character, Node> children;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.children = new HashMap<>();
            this.suffixLink = null;
        }
        int edgeLength(int currentEnd) {
            int end = this.end != -1 ? this.end : currentEnd;
            return end - start;
        }
    }
    @Getter
    private final String text;
    @Getter
    private final int length;
    @Getter
    private final Node root;

    private Node activeNode;
    private int activeEdge;
    private int activeLength;
    private int remaining;
    private int[] end;

    public SuffixTree(String text) {
        this.text = text;
        this.length = text.length();
        this.root = new Node(-1, -1);
        this.root.suffixLink = this.root;

        this.activeNode = this.root;
        this.activeEdge = -1;
        this.activeLength = 0;
        this.remaining = 0;
        this.end = new int[this.length];
        this.end[0] = 0;

        build();

        //buildSuffixTree();
    }

    private void build() {
        for (int i = 1; i < this.length; i++) {
            extend(i);
        }
    }
    private void extend(int pos) {
        this.end[0] = pos + 1;
        this.remaining++;
        Node lastNewNode = null;
        while (this.remaining > 0) {
            if (this.activeLength == 0) {
                this.activeEdge = pos;
            }
            char c = this.text.charAt(this.activeEdge);
            if (!this.activeNode.children.containsKey(c)) {
                // Rule 2: no edge starting with c -> create leaf
                this.activeNode.children.put(c, new Node(pos, -1));
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = this.activeNode;
                    lastNewNode = null;
                }
            } else {
                Node nextNode = this.activeNode.children.get(c);
                int edgeLength = nextNode.edgeLength(this.end[0]);

                // walk down if activeLength >= edgeLength (canonicalize)
                if (this.activeLength >= edgeLength) {
                    this.activeEdge += edgeLength;
                    this.activeLength -= edgeLength;
                    this.activeNode = nextNode;
                    continue;
                }

                // Rule 3: next char on edge matches -> stop
                if (this.text.charAt(nextNode.start + this.activeLength) == this.text.charAt(pos)) {
                    this.activeLength++;
                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = this.activeNode;
                    }
                    break;
                }

                // Rule 2: (mid-edge split)
                Node splitNode = new Node(nextNode.start, nextNode.start + this.activeLength);
                this.activeNode.children.put(c, splitNode);
                splitNode.children.put(this.text.charAt(pos), new Node(pos, -1));
                nextNode.start += this.activeLength;
                splitNode.children.put(this.text.charAt(nextNode.start), nextNode);

                if (lastNewNode != null) {
                    lastNewNode.suffixLink = splitNode;
                }
                lastNewNode = splitNode;
            }
            this.remaining--;
            if (this.activeNode == this.root && this.activeLength > 0) {
                this.activeLength--;
                this.activeEdge = pos - this.remaining + 1;
            } else if (this.activeNode.suffixLink != null && this.activeNode != this.root) {
                this.activeNode = this.activeNode.suffixLink;
            } else {
                this.activeNode = this.root;
            }
        }
    }

    private void buildSuffixTree() {
        Node activeNode = root;
        int activeEdge = -1;
        int activeLength = 0;
        int remainder = 0;
        Node lastNewNode = null;
        for (int i = 0; i < text.length(); i++) {
            this.end[0] = i + 1;
            remainder++;
            lastNewNode = null;
            while (remainder > 0) {
                if (activeLength == 0) {
                    activeEdge = i;
                }
                char c = this.text.charAt(activeEdge);
                if (!activeNode.children.containsKey(c)) {
                    activeNode.children.put(c, new Node(i, -1));
                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = activeNode;
                        lastNewNode = null;
                    }
                } else {
                    Node nextNode = activeNode.children.get(c);
                    int edgeLength = nextNode.edgeLength(this.end[0]);
                    if (activeLength >= edgeLength) {
                        activeEdge += edgeLength;
                        activeLength -= edgeLength;
                        activeNode = nextNode;
                        continue;
                    }
                    if (this.text.charAt(nextNode.start + activeLength) == this.text.charAt(i)) {
                        activeLength++;
                        if (lastNewNode != null) {
                            lastNewNode.suffixLink = activeNode;
                            lastNewNode = null;
                            break;
                        }
                    }
                    int splitEnd = nextNode.start + activeLength;
                    Node splitNode = new Node(nextNode.start, splitEnd);
                    activeNode.children.put(c, splitNode);
                    splitNode.children.put(this.text.charAt(i), new Node(i, -1));

                    nextNode.start = splitEnd;
                    splitNode.children.put(this.text.charAt(nextNode.start), nextNode);
                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = splitNode;
                    }
                    lastNewNode = splitNode;
                }
                remainder--;

                if (activeNode == this.root && activeLength > 0) {
                    activeLength--;
                    activeEdge = i - remainder + 1;
                } else if (activeNode != this.root) {
                    activeNode = activeNode.suffixLink != null ? activeNode.suffixLink : this.root;
                }
            }
        }
    }


    public boolean search2(String pattern) {
        Node currentNode = this.root;
        Node matchNode = null;
        int matchLength = 0;
        for (char c: pattern.toCharArray()) {
            if (matchLength == 0) {
                if (currentNode.children.containsKey(c)) {
                    matchNode = currentNode.children.get(c);
                    matchLength = matchNode.end - matchNode.start;
                } else {
                    return false;
                }
            }
            if (this.text.charAt(matchNode.start + matchLength - (matchNode.end - matchNode.start)) == c) {
                matchLength--;
                if (matchLength == 0) {
                    currentNode = matchNode;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean search(String pattern) {
        Node node = this.root;
        int i = 0;
        while (i < pattern.length()) {
            char c = pattern.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            Node child = node.children.get(c);
            int edgeLength = child.edgeLength(this.end[0]);
            int j = 0;
            while (j < edgeLength && i < pattern.length()) {
                if (this.text.charAt(child.start + j) != pattern.charAt(i)) {
                    return false;
                }
                i++;
                j++;
            }
            node = child;
        }
        return true;
    }
}
