package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Random;

public sealed class LinkedList<T extends Comparable<T>> implements List<T> permits Strings {

    private Node<T> root;

    public LinkedList() {
        root = null;
    }

    public static LinkedList<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        for (var i = 0; i < capacity; i++) {
            integerLinkedList.append(random.nextInt(bound));
        }
        return integerLinkedList;
    }

    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> current = root;
        while (current.hasNext()) {
            current = current.next;
        }
        current.next = newNode;
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (!root.hasNext()) {
            root = null;
            return;
        }
        Node<T> current = root;
        while (current.next.hasNext()) {
            current = current.next;
        }
        current.next = null;
    }

    @Override
    public void init(T... elements) {
        for (var elem : elements) {
            append(elem);
        }
    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> current = root;
        for (int i = 0; i < id; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            current = current.next;
        }
        current.value = object;
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> current = root;
        for (int i = 0; i < id; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            current = current.next;
        }
        return current.value;
    }

    @Override
    public T head() {
        if (root == null) {
            return null;
        }
        return root.value;
    }

    @Override
    public T last() {
        if (root == null) {
            return null;
        }
        Node<T> current = root;
        while (current.hasNext()) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (id == 0) {
            root = root.next;
            return;
        }
        Node<T> current = root;
        for (int i = 0; i < id - 1; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Id too large");
            }
            current = current.next;
        }
        Node<T> successor = current.next.next;
        current.next = successor;
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
    public void sort() {

    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

    public int length() {
        int i = 0;
        Node<T> current = root;
        while (current != null) {
            i++;
            current = current.next;
        }
        return i;
    }

    @Algorithm
    public void reverse() {
        Node<T> cur = root;
        Node<T> next, prev = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        root = prev;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        Node<T> current = root;
        StringBuilder sb = new StringBuilder();
        sb.append(current.value);
        while (current.hasNext()) {
            current = current.next;
            sb.append(" -> ").append(current.value);
        }
        return sb.toString();
    }

    private static final class Node<T> {
        private T value;

        private Node<T> next;

        private Node(T value) {
            this.value = value;
            this.next = null;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}