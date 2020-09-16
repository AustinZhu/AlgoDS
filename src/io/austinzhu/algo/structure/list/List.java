package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.*;

public interface List<T> extends Traversable, Operatable<T>, Searchable<T>, Sortable, Interactable<T> {

}
