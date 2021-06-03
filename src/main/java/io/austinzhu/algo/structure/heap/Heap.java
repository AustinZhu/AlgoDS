package io.austinzhu.algo.structure.heap;

import io.austinzhu.algo.interfaces.Interactable;

import java.util.Arrays;

public abstract class Heap<T extends Comparable<T>> implements Interactable<T> {

    protected final T[] nodes;

    protected int length;

    @SuppressWarnings("unchecked")
    protected Heap(int capacity) {
        this.nodes = (T[]) new Comparable[capacity];
        this.length = 0;
    }

    public void search(T node) {

    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }
}
