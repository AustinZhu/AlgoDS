package dev.austinzhu.algo.structure.list;

import dev.austinzhu.algo.interfaces.Operatable;
import dev.austinzhu.algo.interfaces.Searchable;
import dev.austinzhu.algo.interfaces.Sortable;
import dev.austinzhu.algo.structure.Sequence;

public sealed interface List<T extends Comparable<T>>
        extends Operatable<T>, Searchable<T>, Sortable, Sequence<T>
        permits ArrayList, LinkedList {
}
