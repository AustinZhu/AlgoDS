package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Random;

public class LinkedList<T> implements List<T>{

    private Node<T> root;

    public LinkedList() {
        root = null;
    }

    public static LinkedList<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            integerLinkedList.append(random.nextInt(100));
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
        while (iterator.hasNextNode()) {
            iterator = iterator.getNextNode();
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
        while (iterator.hasNextNode()) {
            iterator = iterator.getNextNode();
        }
        iterator.setNextNode(newNode);
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (!root.hasNextNode()) {
            root = null;
            return;
        }
        Node<T> iterator = root;
        while (iterator.getNextNode().hasNextNode()) {
            iterator = iterator.getNextNode();
        }
        iterator.setNextNode(null);
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
            if (!iterator.hasNextNode()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            iterator = iterator.getNextNode();
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
            if (!iterator.hasNextNode()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            iterator = iterator.getNextNode();
        }
        return iterator.getValue();
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        if (id == 0) {
            root = root.getNextNode();
            return;
        }
        Node<T> iterator = root;
        for (int i = 0; i < id - 1; i++) {
            if (!iterator.hasNextNode()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            iterator = iterator.getNextNode();
        }
        Node<T> successor = iterator.getNextNode().getNextNode();
        iterator.setNextNode(successor);
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

    @Override
    public void travel() {

    }

    public boolean isEmpty() {
        return root == null;
    }
}

final class Node<T> {
    private T value;

    private Node<T> nextNode;

    Node(T value) {
        this.value = value;
        this.nextNode = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public boolean hasNextNode() {
        return getNextNode() != null;
    }
}