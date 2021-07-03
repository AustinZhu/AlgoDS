package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.interfaces.Algorithm;

import java.util.Random;

public final class Stack<T extends Comparable<T>> extends LinkedList<T> {

    private final LinkedList<T> list;

    public Stack(LinkedList<T> list) {
        this.list = list;
    }

    public static Stack<Integer> init(int size, int bound, Random random) {
        return new Stack<>(LinkedList.init(size, bound, random));
    }

    @Algorithm
    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        list.append(element);
    }

    @Algorithm
    @Override
    public T eject() throws IndexOutOfBoundsException {
        return list.eject();
    }

    public T peek() throws IndexOutOfBoundsException {
        return list.head();
    }

    @Override
    public void fill(T... elements) {

    }

    @Override
    public String toString() {
        return list.toString();
    }
}
