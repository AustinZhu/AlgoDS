package io.austinzhu.algo.structure.list;

public interface Stack<T extends Comparable<T>> {

    void prepend(T element);

    T pop();

    T head();
}
