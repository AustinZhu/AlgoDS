package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;

import java.util.Objects;
import java.util.Random;

public class RedBlackTree<T> extends BinarySearchTree<T> {

    public static AVLTree<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < capacity; i++) {
            avlTree.append(random.nextInt(bound));
        }
        return avlTree;
    }

    @Override
    public void append(T element) {
        super.append(element);
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        super.delete(id);
    }

    // Hiding
    private static final class Node<T> extends BinarySearchTree.Node<T> {
        Node<T> parent;

        boolean isBlack;

        Node(T value) {
            super(value);
            isBlack = false;
        }

        @Override
        public Node<T> getLeft() {
            return (Node<T>) left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        @Override
        public Node<T> getRight() {
            return (Node<T>) right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
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
