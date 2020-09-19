package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.util.Random;

public class BinarySearchTree<T extends Comparable<T>> extends BaseBinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Node<T> root) {
        super(root);
    }

    public static BinarySearchTree<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        for (int i = 0; i < capacity; i++) {
            binaryTree.append(random.nextInt(100));
        }
        return binaryTree;
    }

    @Override
    public void append(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            setRoot(newNode);
            return;
        }
        Node<T> iterator = getRoot();
        while (iterator != null) {
            if (newNode.getKey() >= iterator.getKey()) {
                if (!iterator.hasRight()) {
                    iterator.setRight(newNode);
                    return;
                }
                iterator = iterator.getRight();
            } else {
                if (!iterator.hasLeft()) {
                    iterator.setLeft(newNode);
                    return;
                }
                iterator = iterator.getLeft();
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

    @Override
    public void delete(int id) throws IndexOutOfBoundsException, ElementNotFoundException {
        super.delete(id);
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    public boolean exist(T element) {
        return super.exist(element);
    }
}