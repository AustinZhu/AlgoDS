package dev.austinzhu.algo.structure.list;

import dev.austinzhu.algo.interfaces.Algorithm;
import dev.austinzhu.algo.interfaces.SearchingAlgorithm;
import dev.austinzhu.algo.interfaces.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;
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
    public void fill(T... elements) {
        for (var elem : elements) {
            append(elem);
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void set(int idx, T object) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> current = root;
        for (int i = 0; i < idx; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            current = current.next;
        }
        current.value = object;
    }

    @Override
    public T get(int idx) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        Node<T> current = root;
        for (int i = 0; i < idx; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Null Head");
            }
            current = current.next;
        }
        return current.value;
    }

    @Override
    public void insert(int idx, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T delete(int idx) throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        T del;
        if (idx == 0) {
            del = root.value;
            root = root.next;
            return del;
        }
        Node<T> current = root;
        for (int i = 0; i < idx - 1; i++) {
            if (!current.hasNext()) {
                throw new IndexOutOfBoundsException("Id too large");
            }
            current = current.next;
        }
        del = current.next.value;
        current.next = current.next.next;
        return del;
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
    public T eject() throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException("Null Head");
        }
        T del;
        if (!root.hasNext()) {
            del = root.value;
            root = null;
        } else {
            Node<T> current = root;
            while (current.next.hasNext()) {
                current = current.next;
            }
            del = current.next.value;
            current.next = null;
        }
        return del;
    }

    @Override
    public void prepend(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        return null;
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
    public int search(T element) {
        return 0;
    }

    @Override
    public int search(T element, int start, int end) {
        return 0;
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return -1;
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
    public int max() {
        return 0;
    }

    @Override
    public int max(int start, int end) {
        return 0;
    }

    @Override
    public int min() {
        return 0;
    }

    @Override
    public int min(int start, int end) {
        return 0;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(int start, int end) {

    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

    @Override
    public void sort(int start, int end, SortingAlgorithm sa) {

    }

    @Override
    public int length() {
        int i = 0;
        Node<T> current = root;
        while (current != null) {
            i++;
            current = current.next;
        }
        return i;
    }

    public boolean hasCycle() {
        if (root == null) {
            return false;
        }
        Node<T> walker = root;
        Node<T> runner = root;
        while (runner.hasNext() && runner.next.hasNext()) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker.equals(runner)) {
                return true;
            }
        }
        return false;
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
    public java.lang.String toString() {
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

    @Override
    public void reverse(int start, int end) {

    }

    @Override
    public void slice(int start, int end) {

    }

    @NotNull
    @Override
    public T[] toArray() {
        return null;
    }

    @NotNull
    @Override
    public T[] toArray(int start, int end) {
        return null;
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