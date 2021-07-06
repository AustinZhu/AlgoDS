package dev.austinzhu.algo.structure.tree;

import java.util.Random;

public final class RedBlackTree<T> extends BinarySearchTree<T> {

    private Node<T> root;

    public static AVLTree<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < capacity; i++) {
            avlTree.append(random.nextInt(bound));
        }
        return avlTree;
    }

    private Node<T> appendRecursive(Node<T> node, T element) {
        if (node == null) {
            return new Node<>(element, null);
        }
        if (element.hashCode() < node.key) {
            node.setLeft(appendRecursive(node.left, element));
        } else if (element.hashCode() > node.key) {
            node.setRight(appendRecursive(node.right, element));
        } else {
            return node;
        }
        if (node.getColor()) {
            Node<T> uncle = node.getUncle();
            if (uncle == null) {
                return node;
            }
            if (uncle.getColor()) {
                node.getParent().setColor(true);
                node.getUncle().setColor(true);
                node.getGrandparent().setColor(false);
            }
        }
        // fixme
        return null;
    }

    @Override
    public void append(T element) {
        if (root == null) {
            root = new Node<>(element, null);
            root.setColor(true);
        }

        super.append(element);
    }

    @Override
    public T delete(int idx) {
        return super.delete(idx);
    }

    private Node<T> getUncle(Node<T> node) {
        return null;
    }

    // Hiding
    static final class Node<T> extends BinarySearchTree.Node<T> {

        Node<T> left;

        Node<T> right;

        RedBlackTree.Node<T> parent;

        boolean isBlack;

        Node(T value, Node<T> node) {
            super(value);
            isBlack = false;
            parent = node;
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

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public boolean getColor() {
            return isBlack;
        }

        public void setColor(boolean black) {
            isBlack = black;
        }

        public Node<T> getGrandparent() {
            if (parent == null) {
                return null;
            }
            return parent.parent;
        }

        public Node<T> getUncle() {
            Node<T> grandparent= getGrandparent();
            if (grandparent == null) {
                return null;
            }
            if (grandparent.left.key == parent.key) {
                return grandparent.right;
            } else {
                return grandparent.left;
            }
        }
    }
}
