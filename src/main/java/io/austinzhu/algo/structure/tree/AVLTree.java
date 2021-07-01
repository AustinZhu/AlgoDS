package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Random;

public final class AVLTree<T> extends BinarySearchTree<T> {

    private Node<T> root;

    public static AVLTree<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (var i = 0; i < capacity; i++) {
            avlTree.append(random.nextInt(bound));
        }
        return avlTree;
    }

    @Algorithm
    @Override
    public void append(T element) {
        setRoot(appendRecursive(root, element));
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        setRoot(deleteRecursive(root, id));
    }

    public Node<T> leftRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> right = root.right;
        root.right = right.left;
        right.left = root;
        return right;
    }

    public Node<T> rightRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> left = root.left;
        root.left = left.right;
        left.right = root;
        return left;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    private Node<T> appendRecursive(Node<T> node, T element) {
        if (node == null) {
            return new Node<>(element);
        }
        if (element.hashCode() < node.key) {
            node.left = appendRecursive(node.left, element);
        } else if (element.hashCode() > node.key) {
            node.right = appendRecursive(node.right, element);
        } else {
            return node;
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() < node.left.key) {
            return rightRotate(node);
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() > node.right.key) {
            return leftRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node<T> deleteRecursive(Node<T> node, int id) {
        if (node == null) {
            throw new ElementNotFoundException("Element not found");
        }
        if (id < node.key) {
            node.left = deleteRecursive(node.left, id);
        } else if (id > node.key) {
            node.right = deleteRecursive(node.right, id);
        } else {
            if (node.isLeaf()) {
                return null;
            }
            if (node.hasLeft() && !node.hasRight()) {
                return node.left;
            }
            if (node.hasRight() && !node.hasLeft()) {
                return node.right;
            }
            if (node.hasLeft() && node.hasRight()) {
                Node<T> succ = node.getSuccessor();
                node.right = deleteRecursive(node.right, succ.key);
                node.key = succ.key;
                node.value = succ.value;
                return node;
            }
        }
        if (node.getBalanceFactor() > 1 && id < node.left.key) {
            return rightRotate(node);
        }
        if (node.getBalanceFactor() > 1 && id > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (node.getBalanceFactor() < -1 && id > node.right.key) {
            return leftRotate(node);
        }
        if (node.getBalanceFactor() < -1 && id < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public String toString() {
        TreePrinter<Node<T>> tp = new TreePrinter<>(n -> n.value.toString(), Node::getLeft, Node::getRight);
        tp.printTree(root);
        return tp.toString();
    }

    // Hiding
    static final class Node<T> extends BinarySearchTree.Node<T> {

        private Node<T> left;

        private Node<T> right;

        private int height;

        Node(T value) {
            super(value);
            this.height = 1;
        }

        public int getHeight() {
            return height;
        }

        @Override
        Node<T> getLeft() {
            return this.left;
        }

        @Override
        Node<T> getRight() {
            return this.right;
        }

        public void updateHeight() {
            if (this.isLeaf()) {
                this.height = 1;
            } else if (!this.hasLeft()) {
                this.height = 1 + right.getHeight();
            } else if (!this.hasRight()) {
                this.height = 1 + left.getHeight();
            } else {
                this.height = 1 + Math.max(left.getHeight(), right.getHeight());
            }
        }

        public int getBalanceFactor() {
            updateHeight();
            if (isLeaf()) {
                return 0;
            } else if (left == null) {
                return -right.height;
            } else if (!this.hasRight()) {
                return left.height;
            } else {
                return left.height - right.height;
            }
        }

        public Node<T> getSuccessor() {
            if (!this.hasRight()) {
                return null;
            }
            Node<T> succ = this.right;
            if (!succ.hasLeft()) {
                return succ;
            }
            while (succ.hasLeft()) {
                succ = succ.left;
            }
            return succ;
        }

        public Node<T> getPredecessor() {
            if (!this.hasLeft()) {
                return null;
            }
            Node<T> pred = this.left;
            if (!pred.hasRight()) {
                return pred;
            }
            while (pred.hasRight()) {
                pred = pred.right;
            }
            return pred;
        }

        @Override
        public boolean hasLeft() {
            return left != null;
        }

        @Override
        public boolean hasRight() {
            return right != null;
        }

        @Override
        public boolean isLeaf() {
            return !hasLeft() && !hasRight();
        }
    }
}