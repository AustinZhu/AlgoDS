package dev.austinzhu.algo.interfaces;

import java.security.NoSuchAlgorithmException;

public interface Sortable {

    void sort();

    void sort(int start, int end);

    void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException;

    void sort(int start, int end, SortingAlgorithm sa) throws NoSuchAlgorithmException;
}

