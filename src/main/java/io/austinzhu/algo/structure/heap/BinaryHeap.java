package io.austinzhu.algo.structure.heap;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public final class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    private final T[] nodes;

    private int length;

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        this.nodes = (T[]) new Comparable[capacity];
        this.length = 0;
    }

    public static BinaryHeap<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(capacity);
        for (var i = 0; i < capacity; i++) {
            binaryHeap.append(random.nextInt(bound));
        }
        return binaryHeap;
    }

    @SafeVarargs
    @Override
    public final void fill(T... elements) {
        for (T e : elements) {
            append(e);
        }
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

    @Algorithm
    @Override
    public void append(T node) {
        if (length >= nodes.length) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        nodes[length] = node;
        int child = length;
        int parent = (length - 1) / 2;
        length++;
        while (parent >= 0) {
            if (nodes[parent].compareTo(nodes[child]) < 0) {
                var temp = nodes[child];
                nodes[child] = nodes[parent];
                nodes[parent] = temp;
                child = parent;
                parent = (parent - 1) / 2;
            } else {
                return;
            }
        }
    }

    @Algorithm
    @Override
    public T eject() {
        if (length == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        length--;
        nodes[0] = nodes[length];
        nodes[length] = null;
        var parent = 0;
        var child = 1;
        while (child < length) {
            if (child + 1 < length && nodes[child].compareTo(nodes[child + 1]) < 0) {
                child++;
            }
            if (nodes[parent].compareTo(nodes[child]) < 0) {
                var temp = nodes[child];
                nodes[child] = nodes[parent];
                nodes[parent] = temp;
                parent = child;
                child = child * 2 + 1;
            } else {
                return null; //fixme
            }
        }
        return null; //fixme
    }

    @Override
    public void prepend(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
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
        var cur = 0;
        while (cur < length) {
            if (nodes[cur].compareTo(element) == 0) {
                return cur;
            }
            if (cur + 1 < length && nodes[cur].compareTo(nodes[cur + 1]) < 0 && element.compareTo(nodes[cur]) > 0) {
                cur++;
            }
            if (element.compareTo(nodes[cur]) < 0) {
                cur = 2 * cur + 1;
            } else {
                throw new ElementNotFoundException("Not Found");
            }
        }
        throw new ElementNotFoundException("Not Found");
    }

    @Override
    public int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return 0;
    }

    @Override
    public boolean exist(T element) {
        try {
            search(element, SearchingAlgorithm.BINARY);
        } catch (ElementNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }
}
