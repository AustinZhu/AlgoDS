package dev.austinzhu.algo.interfaces;

import java.security.NoSuchAlgorithmException;

public interface Searchable<T> {

    int search(T element);

    int search(T element, int start, int end);

    int search(T element, SearchingAlgorithm sa) throws NoSuchAlgorithmException;

    int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException;

    boolean exist(T element);

}

