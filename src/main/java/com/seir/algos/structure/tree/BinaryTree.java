package com.seir.algos.structure.tree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BinaryTree<T extends Comparable<T>> {
    public static class TreeNode<T extends Comparable<T>> {
        @Getter
        T data;
        @Getter
        TreeNode<T> left;
        @Getter
        TreeNode<T> right;
        @Getter
        int depth;


        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        public TreeNode(T data) {
            this(data, null, null);
        }
        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right, int depth) {
            this(data, left, right);
            this.depth = depth;
        }
        public TreeNode(T data, int depth) {
            this(data, null, null, depth);
        }
    }
    @Getter
    TreeNode<T> root;
    @Getter
    int size;
    @Getter
    int height;


    public BinaryTree() {
        this.root = null;
        this.size = 0;
        this.height = 0;
    }

    public BinaryTree(T data) {
        this.root = new TreeNode<T>(data, null, null, 0);
        this.size = 1;
        this.height = 1;
    }

    int updateMaxHeight(int newHeightIndex) {
        int depth = newHeightIndex+1;
        this.height = depth > this.height ? depth : this.height;
        return this.height;
    }

    public void addOld(T data) {
        if (this.root == null) {
            this.root = new TreeNode<T>(data, null, null, 0);
            this.size++;
            this.height = 1;
            return;
        }
        TreeNode<T> currentNode = this.root;
        while (currentNode != null) {
            if (currentNode.data.compareTo(data) == 0) {
                return;
            }
            if (currentNode.data.compareTo(data) > 0) {
                if (currentNode.left == null) {
                    int newHeight = currentNode.depth + 1;
                    currentNode.left = new TreeNode<>(data, null, null, newHeight);
                    this.size++;
                    updateMaxHeight(newHeight);
                    break;
                }
                if (data.compareTo(currentNode.left.data) == 0) {
                    return;
                }
                if (data.compareTo(currentNode.left.data) > 0) {
                    if (currentNode.left.right == null) {
                        int newHeight = currentNode.left.depth + 1;
                        currentNode.left.right = new TreeNode<>(data, null, null, newHeight);
                        this.size++;
                        updateMaxHeight(newHeight);
                        break;
                    }
                    currentNode = currentNode.left.right;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    int newHeight = currentNode.depth + 1;
                    currentNode.right = new TreeNode<>(data, null, null, newHeight);
                    this.size++;
                    updateMaxHeight(newHeight);
                    break;
                }
                if (data.compareTo(currentNode.right.data) == 0) {
                    return;
                }
                if (data.compareTo(currentNode.right.data) > 0) {
                    if (currentNode.right.right == null) {
                        int newHeight = currentNode.right.depth + 1;
                        currentNode.right.right = new TreeNode<>(data, null, null, newHeight);
                        this.size++;
                        updateMaxHeight(newHeight);
                        break;
                    }
                    currentNode = currentNode.right.right;

                } else {
                    if (currentNode.right.left == null) {
                        int newHeight = currentNode.right.depth + 1;
                        currentNode.right.left = new TreeNode<>(data, null, null, newHeight);
                        this.size++;
                        updateMaxHeight(newHeight);
                        break;
                    }
                    currentNode = currentNode.right.left;

                }
            }
        }
    }

    public void add(T data) {
        if (data == null) {
            return;
        }
        if (this.root == null) {
            this.root = new TreeNode<>(data, 0);
            this.size++;
            this.height = 1;
            return;
        }
        TreeNode<T> currentNode = this.root;
        while (currentNode != null) {
            currentNode = addToNode(currentNode, data);

        }
    }

    private TreeNode<T> addToNode(TreeNode<T> currentNode, T data) {
        if (currentNode.data.compareTo(data) == 0) {
            return null;
        } else if (currentNode.data.compareTo(data) > 0) {
            currentNode = addToLeft(currentNode, data);
        } else {
            currentNode = addToRight(currentNode, data);

        }
        return currentNode;
    }

    private TreeNode<T> addToRight(TreeNode<T> currentNode, T data) {
        if (currentNode.right == null) {
            currentNode.right = new TreeNode<>(data, currentNode.depth+1);
            this.size++;
            updateMaxHeight(currentNode.depth+1);
            return null;
        }
        if (currentNode.right.data.compareTo(data) == 0) {
            return null;
        }
        return (currentNode.right.data.compareTo(data) > 0) ?
                addToLeft(currentNode.right, data) :
                addToRight(currentNode.right, data);

    }

    private TreeNode<T> addToLeft(TreeNode<T> currentNode, T data) {
        if (currentNode.left == null) {
            currentNode.left = new TreeNode<>(data, currentNode.depth+1);
            this.size++;
            updateMaxHeight(currentNode.depth+1);
            return null;
        }
        if (currentNode.left.data.compareTo(data) == 0) {
            return null;
        }
        return (currentNode.left.data.compareTo(data) > 0) ?
            addToLeft(currentNode.left, data) :
            addToRight(currentNode.left, data);
    }

    public List<T> depthFirstTraversal() {
        List<T> result = new ArrayList<>();
        if (this.root == null) {
            return result;
        }
        TreeNode<T> currentNode = this.root;
        result.add(currentNode.data);
        TreeNode<T> previousNode = null;
        traverseLeft(result, currentNode, previousNode);
        traverseRight(result, currentNode, previousNode);
        return result;
    }

    private void traverseRight(List<T> result, TreeNode<T> currentNode, TreeNode<T> previousNode) {
        if (currentNode.right != null) {
            result.add(currentNode.right.data);
            currentNode = currentNode.right;
            previousNode = currentNode;
            traverseLeft(result, currentNode, previousNode);
            traverseRight(result, currentNode, previousNode);
        }
    }

    void traverseLeft(List<T> result, TreeNode<T> currentNode, TreeNode<T> previousNode) {
        if (currentNode.left != null) {
            result.add(currentNode.left.data);
            currentNode = currentNode.left;
            previousNode = currentNode;
            traverseLeft(result, currentNode, previousNode);
            traverseRight(result, currentNode, previousNode);
        }
    }

    public List<T> breadthFirstTraversal() {
        List<T> result = new ArrayList<>();
        if (this.root == null) {
            return result;
        }
        TreeNode<T> currentNode = this.root;
        TreeNode<T> previousNode = null;
        result.add(currentNode.data);
        List<TreeNode<T>> nodesToTraverse = new ArrayList<>();
        nodesToTraverse.add(currentNode);
        while (!nodesToTraverse.isEmpty()) {
            nodesToTraverse = traverseNodes(result, nodesToTraverse);
        }

        return result;
    }
    private List<TreeNode<T>> traverseNodes(List<T> result, List<TreeNode<T>> currentNodes) {
        List<TreeNode<T>> nextNodesToTraverse = new ArrayList<>();
        for (TreeNode<T> currentNode : currentNodes) {
            nextNodesToTraverse.addAll(traverseNode(result, currentNode));
        }
        return nextNodesToTraverse;
    }
    private List<TreeNode<T>> traverseNode(List<T> result, TreeNode<T> currentNode) {
        List<TreeNode<T>> nextNodesToTraverse = new ArrayList<>();
        if (currentNode.left != null) {
            result.add(currentNode.left.data);
            nextNodesToTraverse.add(currentNode.left);
        }
        if (currentNode.right != null) {
            result.add(currentNode.right.data);
            nextNodesToTraverse.add(currentNode.right);
        }
        return nextNodesToTraverse;
    }

    public Optional<TreeNode<T>> search(T data) {
        Optional<TreeNode<T>> foundNode = Optional.empty();
        if (this.root == null) {
            return Optional.empty();
        }
        TreeNode<T> currentNode = this.root;
        TreeNode<T> previousNode = null;
        while (currentNode != null) {
            if (currentNode.data.compareTo(data) == 0) {
                foundNode = Optional.of(currentNode);
                break;
            } else if (currentNode.data.compareTo(data) > 0) {
                previousNode = currentNode;
                currentNode = currentNode.left;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.right;
            }
        }
        return foundNode;
    }

    public boolean isTreeBalanced() {
        if (this.root == null) {
            return true;
        }
        int leftHeight = calculateHeight(this.root.getLeft());
        int rightHeight = calculateHeight(this.root.getRight());
        if (leftHeight == -1 || rightHeight == -1) {
            return false;
        }
        int heightDifference = leftHeight - rightHeight;
        return Math.abs(heightDifference) <= 1;
    }

    private int calculateHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public Optional<T> max() {
        if (root == null) {
            return Optional.empty();
        }
        TreeNode<T> currentNode = this.root;
        while (currentNode != null) {
            if (currentNode.right == null) {
                return Optional.of(currentNode.data);
            }
            currentNode = currentNode.right;
        }
        return Optional.empty();
    }
    public Optional<T> min() {
        if (root == null) {
            return Optional.empty();
        }
        TreeNode<T> currentNode = this.root;
        while (currentNode != null) {
            if (currentNode.left == null) {
                return Optional.of(currentNode.data);
            }
            currentNode = currentNode.left;
        }
        return Optional.empty();
    }
}
