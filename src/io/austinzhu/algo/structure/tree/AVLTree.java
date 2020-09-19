package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class AVLTree<T extends Comparable<T>> extends BaseBinaryTree<T> {

    private AVLNode<T> root;

    public static AVLTree<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < capacity; i++) {
            avlTree.append(random.nextInt(100));
            System.out.println(avlTree.toString());
        }
        return avlTree;
    }

    @Override
    public void append(T element) {
        AVLNode<T> newNode = new AVLNode<>(element);
        if (root == null) {
            setRoot(newNode);
            return;
        }
        Deque<AVLNode<T>> avlNodeDeque = new LinkedList<>();
        avlNodeDeque.addLast(root);
        AVLNode<T> iterator;
        while (true) {
            iterator = avlNodeDeque.peekLast();
            assert iterator != null;
            if (newNode.getKey() > iterator.getKey()) {
                if (!iterator.hasRight()) {
                    iterator.setRight(newNode);
                    break;
                }
                iterator = iterator.getRight();
            } else {
                if (!iterator.hasLeft()) {
                    iterator.setLeft(newNode);
                    break;
                }
                iterator = iterator.getLeft();
            }
            avlNodeDeque.addLast(iterator);
        }
        boolean isAtRoot = false;
        while (!isAtRoot) {
            AVLNode<T> prev = avlNodeDeque.removeLast();
            prev.updateHeight();
            AVLNode<T> prevParent = avlNodeDeque.peekLast();
            if (prevParent == null) {
                isAtRoot = true;
            }
            if (prev.getBalanceFactor() > 1 && prev.getLeft().getKey() >= newNode.getKey()) {
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
            if (prev.getBalanceFactor() < -1 && prev.getRight().getKey() >= newNode.getKey()) {
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

    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        super.delete(id);
    }

    public AVLNode<T> leftRotate(AVLNode<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        AVLNode<T> right = root.getRight();
        root.setRight(right.getLeft());
        right.setLeft(root);
        return right;
    }

    public AVLNode<T> rightRotate(AVLNode<T> root) {
        if (root.isLeaf()) {
            return root;
        }
        AVLNode<T> left = root.getLeft();
        root.setLeft(left.getRight());
        left.setRight(root);
        return left;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        TreePrinter<AVLNode<T>> tp = new TreePrinter<>(n -> n.getValue().toString(), AVLNode::getLeft, AVLNode::getRight);
        tp.printTree(getRoot());
        return tp.toString();
    }
}

final class AVLNode<T> extends Node<T> {

    private int balanceFactor;

    private int height;

    private AVLNode<T> left;

    private AVLNode<T> right;

    AVLNode(T value) {
        super(value);
        this.height = 1;
        this.balanceFactor = 0;
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
        updateBalanceFactor();
        return balanceFactor;
    }

    public void updateBalanceFactor() {
        updateHeight();
        if (isLeaf()) {
            this.balanceFactor = 0;
        } else if (left == null) {
            this.balanceFactor = -right.height;
        } else if (!this.hasRight()) {
            this.balanceFactor = left.height;
        } else {
            this.balanceFactor = left.height - right.height;
        }
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
        updateHeight();
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
        updateHeight();
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

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