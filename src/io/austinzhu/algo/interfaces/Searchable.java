package io.austinzhu.algo.interfaces;

public interface Searchable<T> extends Traversable {

    boolean search(T element, SearchingAlgorithm sa);

    boolean exist(T element);

}

