package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.Interactable;

public class Queue<T extends Comparable<T>> implements Interactable<T> {

    private LinkedList<T> list;

    public Queue(LinkedList<T> list) {
        this.list = list;
    }

    public static Queue<Integer> init(int size, int bound) {
        return new Queue<>(LinkedList.init(size, bound));
    }

    @Algorithm
    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        list.append(element);
    }

    @Algorithm
    @Override
    public void eject() throws IndexOutOfBoundsException {
        list.delete(0);
    }

    @Override
    public void init(T... elements) {

    }

    @Override
    public String toString() {
        return list.toString();
    }
}
