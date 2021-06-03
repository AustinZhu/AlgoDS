package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class BinarySearchTree<T> extends BaseBinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Node<T> root) {
        super(root);
    }

    public static BinarySearchTree<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(bound));
        }
        return binaryTree;
    }

    public Integer[] inOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderRecursive(getRoot(), list);
        return (Integer[]) list.toArray();
    }

    public Integer[] preOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        preOrderRecursive(getRoot(), list);
        return (Integer[]) list.toArray();
    }

    public Integer[] postOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        postOrderRecursive(getRoot(), list);
        return (Integer[]) list.toArray();
    }

    public Integer[] levelOrder() {
        if (isEmpty()) {
            return new Integer[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Node<T>> nodeDeque = new LinkedList<>();
        nodeDeque.addLast(getRoot());
        while (!nodeDeque.isEmpty()) {
            Node<T> tmp = nodeDeque.removeFirst();
            list.add(tmp.key);
            if (tmp.hasLeft()) {
                nodeDeque.addLast(tmp.getLeft());
            }
            if (tmp.hasRight()) {
                nodeDeque.addLast(tmp.getRight());
            }
        }
        return (Integer[]) list.toArray();
    }

    @Algorithm
    @Override
    public void append(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            setRoot(newNode);
            size++;
            return;
        }
        Node<T> iterator = getRoot();
        while (iterator != null) {
            if (newNode.getKey() < iterator.getKey()){
                if (!iterator.hasLeft()) {
                    iterator.setLeft(newNode);
                    size++;
                    return;
                }
                iterator = iterator.getLeft();
            } else if (newNode.getKey() > iterator.getKey()) {
                if (!iterator.hasRight()) {
                    iterator.setRight(newNode);
                    size++;
                    return;
                }
                iterator = iterator.getRight();
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
        if (isEmpty()) {
            throw new ElementNotFoundException("Empty root");
        }
        Node<T> iterator = getRoot(), deleted = getRoot();
        boolean isLeft = false;
        while (iterator.getKey() != id) {
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
        boolean isRoot = deleted.getKey() == getRoot().getKey();
        if (deleted.isLeaf()) {
            if (isRoot) {
                setRoot(null);
            } else if (isLeft) {
                iterator.setLeft(null);
            } else {
                iterator.setRight(null);
            }
        }
        if (deleted.hasLeft() && !deleted.hasRight()) {
            if (isRoot) {
                setRoot(deleted.getLeft());
            } else if (isLeft) {
                iterator.setLeft(deleted.getLeft());
            } else {
                iterator.setRight(deleted.getLeft());
            }
        }
        if (deleted.hasRight() && !deleted.hasLeft()) {
            if (isRoot) {
                setRoot(deleted.getRight());
            } else if (isLeft) {
                iterator.setLeft(deleted.getRight());
            } else {
                iterator.setRight(deleted.getRight());
            }
        }
        if (deleted.hasLeft() && deleted.hasRight()) {
            Node<T> succ = deleted.ejectSuccessor();
            succ.setLeft(deleted.getLeft());
            succ.setRight(deleted.getRight());
            if (isRoot) {
                setRoot(succ);
            } else if (isLeft) {
                iterator.setLeft(succ);
            } else {
                iterator.setRight(succ);
            }
        }
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    public boolean exist(T element) {
        return super.exist(element);
    }

    private void preOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.key);
        preOrderRecursive(node.getLeft(), list);
        preOrderRecursive(node.getRight(), list);
    }

    private void inOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.getLeft(), list);
        list.add(node.key);
        inOrderRecursive(node.getRight(), list);
    }

    private void postOrderRecursive(Node<T> node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.getLeft(), list);
        postOrderRecursive(node.getRight(), list);
        list.add(node.key);
    }
}