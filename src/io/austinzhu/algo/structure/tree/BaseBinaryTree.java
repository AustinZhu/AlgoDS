package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.*;

public abstract class BaseBinaryTree<T> implements Interactable<T>, Operatable<T>, Searchable<T>, Traversable {
    private Node<T> root;

    @Override
    public void append(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public void eject() throws IndexOutOfBoundsException {

    }

    @Override
    public void init(T... elements) {

    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {

    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return -1;
    }

    @Override
    public boolean exist(T element) {
        return false;
    }

    @Override
    public void travel() {

    }

    public void balance() {

    }

    public int getHeight() {
        return 0;
    }
}

final class Node<T> {
    private T value;

    private Node<T> left;

    private Node<T> right;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
