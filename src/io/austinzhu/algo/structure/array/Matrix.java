package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.lang.reflect.GenericArrayType;

public class Matrix<T extends GenericArrayType & Comparable<T>> extends BaseArray<T>{

    Matrix (int dimension) {
        super(dimension);
    }

    @Override
    public boolean search(T element, SearchingAlgorithm sa) {
        return false;
    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }
}
