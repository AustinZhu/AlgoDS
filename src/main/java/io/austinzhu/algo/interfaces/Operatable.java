package io.austinzhu.algo.interfaces;

public interface Operatable<T> {

    void fill(T... elements);

    void clear();

    void set(int idx, T object) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    T get(int idx) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void insert(int idx, T object) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    T delete(int idx) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void append(T element) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    T eject() throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void prepend(T element) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    T pop() throws io.austinzhu.algo.exception.IndexOutOfBoundsException;
}
