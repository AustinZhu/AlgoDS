package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.Random;

public sealed class BinarySearchTree<T>
        extends BinaryTree<T>
        permits AVLTree, RedBlackTree {

    private Node<T> root;

    public static BinarySearchTree<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        for (var i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(bound));
        }
        return binaryTree;
    }

    @Algorithm
    @Override
    public void append(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            setRoot(newNode);
            size++;
            return;
        }
        Node<T> iterator = root;
        while (iterator != null) {
            if (newNode.key < iterator.key) {
                if (!iterator.hasLeft()) {
                    iterator.left = newNode;
                    size++;
                    return;
                }
                iterator = iterator.left;
            } else if (newNode.key > iterator.key) {
                if (!iterator.hasRight()) {
                    iterator.right = newNode;
                    size++;
                    return;
                }
                iterator = iterator.right;
            } else {
                return;
            }
        }
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException, ElementNotFoundException {
        super.set(id, object);
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        return super.get(id);
    }

    @Algorithm
    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (root == null) {
            throw new ElementNotFoundException("Empty root");
        }
        Node<T> iterator = root, deleted = root;
        boolean isLeft = false;
        while (iterator.key != id) {
            if (id < iterator.key) {
                if (!iterator.hasLeft()) {
                    throw new ElementNotFoundException("Not found");
                }
                if (id == iterator.left.key) {
                    deleted = iterator.left;
                    isLeft = true;
                    break;
                }
                iterator = iterator.left;
            } else {
                if (!iterator.hasRight()) {
                    throw new ElementNotFoundException("Not found");
                }
                if (id == iterator.right.key) {
                    deleted = iterator.right;
                    isLeft = false;
                    break;
                }
                iterator = iterator.right;
            }
        }
        boolean isRoot = deleted.key == root.key;
        if (deleted.isLeaf()) {
            if (isRoot) {
                setRoot(null);
            } else if (isLeft) {
                iterator.left = null;
            } else {
                iterator.right = null;
            }
        }
        if (deleted.hasLeft() && !deleted.hasRight()) {
            if (isRoot) {
                setRoot(deleted.left);
            } else if (isLeft) {
                iterator.left = deleted.left;
            } else {
                iterator.right = deleted.left;
            }
        }
        if (deleted.hasRight() && !deleted.hasLeft()) {
            if (isRoot) {
                setRoot(deleted.right);
            } else if (isLeft) {
                iterator.left = deleted.right;
            } else {
                iterator.right = deleted.right;
            }
        }
        if (deleted.hasLeft() && deleted.hasRight()) {
            Node<T> succ = (Node<T>) ejectSuccessor(deleted);
            succ.left = deleted.left;
            succ.right = deleted.right;
            if (isRoot) {
                setRoot(succ);
            } else if (isLeft) {
                iterator.left = succ;
            } else {
                iterator.right = succ;
            }
        }
    }

    private Node<T> ejectPredecessor(Node<T> node) {
        if (!node.hasLeft()) {
            return null;
        }
        Node<T> parent = node;
        Node<T> pred = node.left;
        if (!pred.hasRight()) {
            parent.left = pred.left;
            return pred;
        }
        while (pred.hasRight()) {
            parent = pred;
            pred = pred.right;
        }
        parent.right = null;
        return pred;
    }

    private Node<T> ejectSuccessor(Node<T> node) {
        if (!node.hasRight()) {
            return null;
        }
        Node<T> parent = node;
        Node<T> succ = node.right;
        if (!succ.hasLeft()) {
            parent.right = succ.right;
            return succ;
        }
        while (succ.hasLeft()) {
            parent = succ;
            succ = succ.left;
        }
        parent.left = null;
        return succ;
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    public boolean exist(T element) {
        return super.exist(element);
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        TreePrinter<Node<T>> tp = new TreePrinter<>(n -> n.value.toString(), Node::getLeft, Node::getRight);
        ;
        tp.printTree(root);
        return tp.toString();
    }

    static sealed class Node<T>
            extends BinaryTree.Node<T>
            permits AVLTree.Node, RedBlackTree.Node {

        private Node<T> left;

        private Node<T> right;

        Node(T value) {
            super(value);
        }

        @Override
        Node<T> getLeft() {
            return this.left;
        }

        @Override
        Node<T> getRight() {
            return this.right;
        }

        @Override
        public boolean hasLeft() {
            return left != null;
        }

        @Override
        public boolean hasRight() {
            return right != null;
        }
    }
}