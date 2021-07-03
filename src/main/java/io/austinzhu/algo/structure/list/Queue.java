package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Random;

public final class Queue<T extends Comparable<T>> extends LinkedList<T> {

    private LinkedList<T> list;

    public Queue(LinkedList<T> list) {
        this.list = list;
    }

    public static Queue<Integer> init(int size, int bound, Random random) {
        return new Queue<>(LinkedList.init(size, bound, random));
    }

    @Algorithm
    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        list.append(element);
    }

    @Algorithm
    @Override
    public T eject() throws IndexOutOfBoundsException {
        return list.delete(0);
    }

    public T peek() throws IndexOutOfBoundsException {
        return list.get(0);
    }

    @Override
    public void fill(T... elements) {

    }

    @Override
    public String toString() {
        return list.toString();
    }
}
