package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

public class Stack<T extends Comparable<T>> extends BaseArray<T>{

    public Stack(int capacity) {
        super(capacity);
    }

    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        if (getUpperBound() - getLowerBound() <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int upperBound = getUpperBound();
        setUpperBound(++upperBound);
        set(upperBound - 1, element);
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (getUpperBound() - getLowerBound() <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int upperBound = getUpperBound();
        set(upperBound - 1, null);
        setUpperBound(--upperBound);
    }

    public T get() {
        return get(getUpperBound() - 1);
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return -1;
    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }
}
