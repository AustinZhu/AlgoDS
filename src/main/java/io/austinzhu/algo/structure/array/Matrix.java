package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

public class Matrix<T extends Comparable<T>> extends BaseArray<T>{

    Matrix (int dimension) {
        super(dimension);
    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return -1;
    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
