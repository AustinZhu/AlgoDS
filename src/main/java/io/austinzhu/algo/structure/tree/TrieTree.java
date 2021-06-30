package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

public class TrieTree<T> implements Tree<T> {


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
        return 0;
    }

    @Override
    public boolean exist(T element) {
        return false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    private static final class Node<T> {

        private final Node<T>[] children = new Node[128];

        private T value;

        private boolean isEnd;

        private Node(T value) {
            this.value = value;
            this.isEnd = false;
        }
    }
}
