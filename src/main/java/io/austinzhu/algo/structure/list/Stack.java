package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.Interactable;

public class Stack<T extends Comparable<T>> implements Interactable<T> {

    private final LinkedList<T> list;

    public Stack(LinkedList<T> list) {
        this.list = list;
    }

    public static Stack<Integer> init(int size, int bound) {
        return new Stack<>(LinkedList.init(size, bound));
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

    @Override
    public void init(T... elements) {

    }

    @Override
    public String toString() {
        return list.toString();
    }
}
