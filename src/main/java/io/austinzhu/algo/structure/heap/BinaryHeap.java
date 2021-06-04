package io.austinzhu.algo.structure.heap;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Random;

public class BinaryHeap<T extends Comparable<T>> extends Heap<T> {

    public BinaryHeap(int capacity) {
        super(capacity);
    }

    public static BinaryHeap<Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(13);
        for (int i = 0; i < capacity; i++) {
            binaryHeap.append(random.nextInt(bound));
        }
        return binaryHeap;
    }

    @Override
    public void init(T... elements) {
        for (T e : elements) {
            append(e);
        }
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
                T temp = nodes[child];
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
    public void eject() {
        if (length == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        length--;
        nodes[0] = nodes[length];
        nodes[length] = null;
        int parent = 0;
        int child = 1;
        while (child < length) {
            if (child + 1 < length && nodes[child].compareTo(nodes[child + 1]) < 0) {
                child++;
            }
            if (nodes[parent].compareTo(nodes[child]) < 0) {
                T temp = nodes[child];
                nodes[child] = nodes[parent];
                nodes[parent] = temp;
                parent = child;
                child = child * 2 + 1;
            } else {
                return;
            }
        }
    }
}
