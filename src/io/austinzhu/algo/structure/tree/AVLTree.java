package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Deque;
import java.util.LinkedList;
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

    @Algorithm
    @Override
    public void append(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            setRoot(newNode);
            return;
        }
        Deque<Node<T>> avlNodeDeque = new LinkedList<>();
        Node<T> iterator = root;
        // DFS Search for insertion position
        while (true) {
            avlNodeDeque.addLast(iterator);
            if (newNode.getKey() > iterator.getKey()) {
                if (!iterator.hasRight()) {
                    iterator.setRight(newNode);
                    break;
                }
                iterator = iterator.getRight();
            } else if (newNode.getKey() < iterator.getKey()){
                if (!iterator.hasLeft()) {
                    iterator.setLeft(newNode);
                    break;
                }
                iterator = iterator.getLeft();
            } else {
                return;
            }
        }
        boolean isAtRoot = false;
        while (!isAtRoot) {
            Node<T> prev = avlNodeDeque.removeLast();
            prev.updateHeight();
            Node<T> prevParent = avlNodeDeque.peekLast();
            if (prevParent == null) {
                isAtRoot = true;
            }
            // Balance Factor:
            //  0  : Balanced
            //  >1 : Left heavy
            //  <-1: Right heavy

            // Case 1: caused imbalance on LEFT child's LEFT subtree
            if (prev.getBalanceFactor() > 1 && prev.getLeft().getKey() > newNode.getKey()) {
                if (isAtRoot) {
                    setRoot(rightRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(rightRotate(prev));
                } else {
                    prevParent.setRight(rightRotate(prev));
                }
            }
            // Case 2: caused imbalance on LEFT child's RIGHT subtree
            if (prev.getBalanceFactor() > 1 && prev.getLeft().getKey() < newNode.getKey()) {
                prev.setLeft(leftRotate(prev.getLeft()));
                if (isAtRoot) {
                    setRoot(rightRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(rightRotate(prev));
                } else {
                    prevParent.setRight(rightRotate(prev));
                }
            }
            // Case 3: caused imbalance on RIGHT child's RIGHT subtree
            if (prev.getBalanceFactor() < -1 && prev.getRight().getKey() < newNode.getKey()) {
                if (isAtRoot) {
                    setRoot(leftRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(leftRotate(prev));
                } else {
                    prevParent.setRight(leftRotate(prev));
                }
            }
            // Case 2: caused imbalance on RIGHT child's LEFT subtree
            if (prev.getBalanceFactor() < -1 && prev.getRight().getKey() > newNode.getKey()) {
                prev.setRight(rightRotate(prev.getRight()));
                if (isAtRoot) {
                    setRoot(leftRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(leftRotate(prev));
                } else {
                    prevParent.setRight(leftRotate(prev));
                }
            }
        }
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (root == null) {
            throw new ElementNotFoundException("Empty root");
        }
        Deque<Node<T>> avlNodeDeque = new LinkedList<>();
        Node<T> iterator = root, deleted = root;
        boolean isLeft = false;
        while (iterator.getKey() != id) {
            avlNodeDeque.addLast(iterator);
            if (id < iterator.getKey()) {
                if (!iterator.hasLeft()) {
                    throw new ElementNotFoundException("Not found");
                }
                if (id == iterator.getLeft().getKey()) {
                    deleted = iterator.getLeft();
                    isLeft = true;
                    break;
                }
                iterator = iterator.getLeft();
            } else {
                if (!iterator.hasRight()) {
                    throw new ElementNotFoundException("Not found");
                }
                if (id == iterator.getRight().getKey()) {
                    deleted = iterator.getRight();
                    isLeft = false;
                    break;
                }
                iterator = iterator.getRight();
            }
        }
        if (deleted.isLeaf()) {
            if (isLeft) {
                iterator.setLeft(null);
            } else {
                iterator.setRight(null);
            }
        }
        if (deleted.hasLeft() && !deleted.hasRight()) {
            if (isLeft) {
                iterator.setLeft(deleted.getLeft());
            } else {
                iterator.setRight(deleted.getLeft());
            }
        }
        if (deleted.hasRight() && !deleted.hasLeft()) {
            if (isLeft) {
                iterator.setLeft(deleted.getRight());
            } else {
                iterator.setRight(deleted.getRight());
            }
        }
        if (deleted.hasLeft() && deleted.hasRight()) {
            Node<T> succ = deleted.ejectSuccessor();
            succ.setLeft(deleted.getLeft());
            succ.setRight(deleted.getRight());
            if (isLeft) {
                iterator.setLeft(succ);
            } else {
                iterator.setRight(succ);
            }
        }
        boolean isAtRoot = false;
        while (!isAtRoot) {
            Node<T> prev = avlNodeDeque.removeLast();
            prev.updateHeight();
            Node<T> prevParent = avlNodeDeque.peekLast();
            if (prevParent == null) {
                isAtRoot = true;
            }
            // Balance Factor:
            //  0  : Balanced
            //  >1 : Left heavy
            //  <-1: Right heavy

            // Case 1: caused imbalance on LEFT child's LEFT subtree
            if (prev.getBalanceFactor() > 1 && prev.getLeft().getKey() > id) {
                if (isAtRoot) {
                    setRoot(rightRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(rightRotate(prev));
                } else {
                    prevParent.setRight(rightRotate(prev));
                }
            }
            // Case 2: caused imbalance on LEFT child's RIGHT subtree
            if (prev.getBalanceFactor() > 1 && prev.getLeft().getKey() < id) {
                prev.setLeft(leftRotate(prev.getLeft()));
                if (isAtRoot) {
                    setRoot(rightRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(rightRotate(prev));
                } else {
                    prevParent.setRight(rightRotate(prev));
                }
            }
            // Case 3: caused imbalance on RIGHT child's RIGHT subtree
            if (prev.getBalanceFactor() < -1 && prev.getRight().getKey() < id) {
                if (isAtRoot) {
                    setRoot(leftRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(leftRotate(prev));
                } else {
                    prevParent.setRight(leftRotate(prev));
                }
            }
            // Case 2: caused imbalance on RIGHT child's LEFT subtree
            if (prev.getBalanceFactor() < -1 && prev.getRight().getKey() > id) {
                prev.setRight(rightRotate(prev.getRight()));
                if (isAtRoot) {
                    setRoot(leftRotate(root));
                    return;
                }
                if (prevParent.getLeft().equals(prev)) {
                    prevParent.setLeft(leftRotate(prev));
                } else {
                    prevParent.setRight(leftRotate(prev));
                }
            }
        }
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