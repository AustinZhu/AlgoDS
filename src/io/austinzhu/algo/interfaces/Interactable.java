package io.austinzhu.algo.interfaces;

public interface Interactable<T> {

    void append(T element) throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void eject() throws io.austinzhu.algo.exception.IndexOutOfBoundsException;

    void init(T... elements);
}
