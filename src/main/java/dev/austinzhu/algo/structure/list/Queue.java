package dev.austinzhu.algo.structure.list;

public interface Queue<T extends Comparable<T>> {

    void append(T element);

    T pop();

    T head();
}
