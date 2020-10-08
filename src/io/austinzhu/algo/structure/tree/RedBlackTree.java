package io.austinzhu.algo.structure.tree;

import java.util.Objects;

public class RedBlackTree<T> extends BinarySearchTree<T> {

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
