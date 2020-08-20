package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

public class Queue<T extends Comparable<T>> extends BaseArray<T> {
    public Queue(int capacity) {
        super(capacity);
    }

    @Override
    public void eject() throws IndexOutOfBoundsException {
        if (getUpperBound() - getLowerBound() <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int lowerBound = getLowerBound();
        set(lowerBound, null);
        lowerBound++;
        setLowerBound(lowerBound);
    }

    @Override
    public boolean search(T element, SearchingAlgorithm sa) {
        return false;
    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }
}
