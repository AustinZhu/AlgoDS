package io.austinzhu.algo.structure.tree;

import io.austinzhu.algo.interfaces.Interactable;
import io.austinzhu.algo.interfaces.Operatable;
import io.austinzhu.algo.interfaces.Searchable;

public interface Tree<T> extends Interactable<T>, Operatable<T>, Searchable<T> {

    int getHeight();

}
