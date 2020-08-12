package io.austinzhu.algo.interfaces;

public interface Operatable<T> {

    void set(int id, T object) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    T get(int id) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void delete(int id) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

}
