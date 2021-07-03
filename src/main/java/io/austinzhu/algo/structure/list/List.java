package io.austinzhu.algo.structure.list;

import io.austinzhu.algo.interfaces.Operatable;
import io.austinzhu.algo.interfaces.Searchable;
import io.austinzhu.algo.interfaces.Sortable;

public sealed interface List<T extends Comparable<T>>
        extends Operatable<T>, Searchable<T>, Sortable
        permits LinkedList {

    T head();

    T last();
}
