package io.austinzhu.algo.interfaces;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;

public interface Sortable {

    void sort();

    void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException;

}

