package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.security.NoSuchAlgorithmException;

public class TrieTree<T> implements Tree<T> {


    @Override
    public void append(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T eject() throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void prepend(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void fill(T... elements) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void set(int idx, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T get(int idx) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void insert(int idx, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T delete(int idx) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public int search(T element) {
        return 0;
    }

    @Override
    public int search(T element, int start, int end) {
        return 0;
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return 0;
    }

    @Override
    public int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
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
