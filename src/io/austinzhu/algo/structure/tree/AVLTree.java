package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Objects;
import java.util.Random;

public class AVLTree<T> extends BinarySearchTree<T> {

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
            return new Node<>(element);
        }
        if (element.hashCode() < node.getKey()) {
            node.setLeft(appendRecursive(node.getLeft(), element));
        } else if (element.hashCode() > node.getKey()) {
            node.setRight(appendRecursive(node.getRight(), element));
        } else {
            return node;
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() < node.getLeft().getKey()) {
            return rightRotate(node);
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() > node.getRight().getKey()) {
            return leftRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() < node.getRight().getKey()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    @Algorithm
    @Override
    public void append(T element) {
        setRoot(appendRecursive(root, element));
    }

    private Node<T> deleteRecursive(Node<T> node, T element) {
        if (node == null) {
            throw new ElementNotFoundException("Element not found");
        }
        if (element.hashCode() < node.getKey()) {
            node.setLeft(deleteRecursive(node.getLeft(), element));
        } else if (element.hashCode() > node.getKey()) {
            node.setRight(deleteRecursive(node.getRight(), element));
        } else {
            if (node.isLeaf()) {
                return null;
            }
            if (node.hasLeft() && !node.hasRight()) {
                return node.getLeft();
            }
            if (node.hasRight() && !node.hasLeft()) {
                return node.getRight();
            }
            if (node.hasLeft() && node.hasRight()) {
                Node<T> succ = node.ejectSuccessor();
                succ.setLeft(node.getLeft());
                succ.setRight(node.getRight());
                return succ;
            }
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() < node.getLeft().getKey()) {
            return rightRotate(node);
        }
        if (node.getBalanceFactor() > 1 && element.hashCode() > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() > node.getRight().getKey()) {
            return leftRotate(node);
        }
        if (node.getBalanceFactor() < -1 && element.hashCode() < node.getRight().getKey()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    @Algorithm
    public void delete(T element) throws IndexOutOfBoundsException, ElementNotFoundException {
        setRoot(deleteRecursive(root, element));
    }

    public Node<T> leftRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> right = root.getRight();
        root.setRight(right.getLeft());
        right.setLeft(root);
        return right;
    }

    public Node<T> rightRotate(Node<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        Node<T> left = root.getLeft();
        root.setLeft(left.getRight());
        left.setRight(root);
        return left;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Hiding
    private static final class Node<T> extends BinarySearchTree.Node<T> {

        private int height;

        Node(T value) {
            super(value);
            this.height = 1;
        }

        public int getHeight() {
            return height;
        }

        public void updateHeight() {
            if (this.isLeaf()) {
                this.height = 1;
            } else if (!this.hasLeft()) {
                this.height = 1 + getRight().getHeight();
            } else if (!this.hasRight()) {
                this.height = 1 + getLeft().getHeight();
            } else {
                this.height = 1 + Math.max(getLeft().getHeight(), getRight().getHeight());
            }
        }

        public int getBalanceFactor() {
            updateHeight();
            if (isLeaf()) {
                return 0;
            } else if (left == null) {
                return -getRight().height;
            } else if (!this.hasRight()) {
                return getLeft().height;
            } else {
                return getLeft().height - getRight().height;
            }
        }

        @Override
        public Node<T> ejectSuccessor() {
            if (!this.hasRight()) {
                return null;
            }
            Node<T> parent = this;
            Node<T> succ = this.getRight();
            if (!succ.hasLeft()) {
                parent.setRight(succ.getRight());
                return succ;
            }
            while (succ.hasLeft()) {
                parent = succ;
                succ = succ.getLeft();
            }
            parent.setLeft(null);
            return succ;
        }

        @Override
        public Node<T> ejectPredecessor() {
            if (!this.hasLeft()) {
                return null;
            }
            Node<T> parent = this;
            Node<T> pred = this.getLeft();
            if (!pred.hasRight()) {
                parent.setLeft(pred.getLeft());
                return pred;
            }
            while (pred.hasRight()) {
                parent = pred;
                pred = pred.getRight();
            }
            parent.setRight(null);
            return pred;
        }

        @Override
        public Node<T> getLeft() {
            return (Node<T>) left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
            updateHeight();
        }

        @Override
        public Node<T> getRight() {
            return (Node<T>) right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
            updateHeight();
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

        @Override
        public int hashCode() {
            int h = Objects.hashCode(getValue());
            h = h * 31 + Objects.hashCode(left);
            h = h * 31 + Objects.hashCode(right);
            return h;
        }
    }
}