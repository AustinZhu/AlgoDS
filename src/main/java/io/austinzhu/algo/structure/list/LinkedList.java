package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Random;

public final class LinkedList<T extends Comparable<T>> implements List<T> {

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
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        Node<T> iterator = root;
        StringBuilder sb = new StringBuilder();
        sb.append(iterator.getValue());
        while (iterator.hasNext()) {
            iterator = iterator.getNext();
            sb.append(" -> ").append(iterator.getValue());
        }
        return sb.toString();
    }

    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        Node<T> iterator = root;
        while (iterator.hasNext()) {
            iterator = iterator.getNext();
        }
        iterator.setNext(newNode);
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (!root.hasNext()) {
            root = null;
            return;
        }
        Node<T> iterator = root;
        while (iterator.getNext().hasNext()) {
            iterator = iterator.getNext();
        }
        iterator.setNext(null);
    }

    @Override
    public void init(T... elements) {

    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> iterator = root;
        for (int i = 0; i < id; i++) {
            if (!iterator.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            iterator = iterator.getNext();
        }
        iterator.setValue(object);
    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> iterator = root;
        for (int i = 0; i < id; i++) {
            if (!iterator.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    @Override
    public T head() {
        if (isEmpty()) {
            return null;
        }
        return root.getValue();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            return null;
        }
        Node<T> iterator = root;
        while (iterator.hasNext()) {
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (id == 0) {
            root = root.getNext();
            return;
        }
        Node<T> iterator = root;
        for (int i = 0; i < id - 1; i++) {
            if (!iterator.hasNext()) {
                throw new IndexOutOfBoundsException("Id too large");
            }
            iterator = iterator.getNext();
        }
        Node<T> successor = iterator.getNext().getNext();
        iterator.setNext(successor);
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
        if (root == null) {
            return 0;
        }
        int i = 1;
        Node<T> iterator = root;
        while (iterator.hasNext()) {
            i++;
            iterator = iterator.next;
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

    public boolean isEmpty() {
        return root == null;
    }

    private static final class Node<T> {
        private T value;

        private Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return getNext() != null;
        }
    }
}