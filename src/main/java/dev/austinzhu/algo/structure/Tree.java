package dev.austinzhu.algo.structure;

import dev.austinzhu.algo.interfaces.Operatable;
import dev.austinzhu.algo.interfaces.Searchable;

public interface Tree<T> extends Operatable<T>, Searchable<T> {

    int getHeight();

    int getSize();

    class Node<T> {

        public int key;

        public T value;
    }
}
