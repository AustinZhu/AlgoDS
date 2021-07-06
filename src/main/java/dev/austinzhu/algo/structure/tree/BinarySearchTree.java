package dev.austinzhu.algo.structure.tree;

import dev.austinzhu.algo.interfaces.Algorithm;
import dev.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.NoSuchElementException;
import java.util.Random;

public sealed class BinarySearchTree<T>
        extends BinaryTree<T>
        permits AVLTree, RedBlackTree {

    private BinaryTree.Node<T> root;

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
        BinaryTree.Node<T> iterator = root;
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
    public void set(int idx, T object) throws IndexOutOfBoundsException, NoSuchElementException {
        super.set(idx, object);
    }

    @Override
    public T get(int idx) throws IndexOutOfBoundsException, NoSuchElementException {
        return super.get(idx);
    }

    @Algorithm
    @Override
    public T delete(int idx) throws IndexOutOfBoundsException, NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException("Empty root");
        }
        BinaryTree.Node<T> iterator = root, deleted = root;
        boolean isLeft = false;
        while (iterator.key != idx) {
            if (idx < iterator.key) {
                if (!iterator.hasLeft()) {
                    throw new NoSuchElementException("Not found");
                }
                if (idx == iterator.left.key) {
                    deleted = iterator.left;
                    isLeft = true;
                    break;
                }
                iterator = iterator.left;
            } else {
                if (!iterator.hasRight()) {
                    throw new NoSuchElementException("Not found");
                }
                if (idx == iterator.right.key) {
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
            BinaryTree.Node<T> succ = ejectSuccessor(deleted);
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
        return null; //fixme
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

    @Override
    public void setRoot(BinaryTree.Node<T> root) {
        this.root = root;
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    public boolean exist(T element) {
        return super.exist(element);
    }

    @Override
    public String toString() {
        TreePrinter<BinaryTree.Node<T>> tp = new TreePrinter<BinaryTree.Node<T>>(n -> n.value.toString(), BinaryTree.Node::getLeft, BinaryTree.Node::getRight);
        ;
        tp.printTree(root);
        return tp.toString();
    }

    private BinaryTree.Node<T> ejectSuccessor(BinaryTree.Node<T> node) {
        if (!node.hasRight()) {
            return null;
        }
        BinaryTree.Node<T> parent = node;
        BinaryTree.Node<T> succ = node.right;
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
}