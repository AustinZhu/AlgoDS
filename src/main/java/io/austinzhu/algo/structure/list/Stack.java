package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.Interactable;

import java.util.Random;

public class Stack<T extends Comparable<T>> implements Interactable<T> {

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
    public void eject() throws IndexOutOfBoundsException {
        list.eject();
    }

    public T peek() throws IndexOutOfBoundsException {
        return list.last();
    }

    @Override
    public void init(T... elements) {

    }

    @Override
    public String toString() {
        return list.toString();
    }
}
