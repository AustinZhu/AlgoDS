package io.austinzhu.algo.interfaces;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;

public interface Sortable extends Traversable {

    void sort();

    void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException;

}

