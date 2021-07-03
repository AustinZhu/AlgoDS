package io.austinzhu.algo.structure.heap;

import io.austinzhu.algo.interfaces.Operatable;
import io.austinzhu.algo.interfaces.Searchable;

public sealed interface Heap<T extends Comparable<T>>
        extends Operatable<T>, Searchable<T>
        permits BinaryHeap {
}