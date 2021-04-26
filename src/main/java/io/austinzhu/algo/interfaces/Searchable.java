package io.austinzhu.algo.interfaces;

public interface Searchable<T> {

    int search(T element, SearchingAlgorithm sa);

    boolean exist(T element);

}

