package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.*;

public abstract class List<T extends Comparable<T>> implements Operatable<T>, Searchable<T>, Sortable, Interactable<T> {
    @Override
    public void append(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public void eject() throws IndexOutOfBoundsException {

    }

    @Override
    public void init(T... elements) {

    }

    @Override
    public void set(int id, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T get(int id) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {

    }

    @Override
    public int search(T element, SearchingAlgorithm sa) {
        return 0;
    }

    @Override
    public boolean exist(T element) {
        return false;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {

    }

}
