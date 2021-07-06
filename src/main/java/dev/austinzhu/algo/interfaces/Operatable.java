package dev.austinzhu.algo.interfaces;

public interface Operatable<T> {

    void fill(T... elements);

    void clear();

    void set(int idx, T object);

    T get(int idx);

    void insert(int idx, T object);

    T delete(int idx);

    void append(T element);

    T eject();

    void prepend(T element);

    T pop();
}
