package dev.austinzhu.algo.structure.heap;

import dev.austinzhu.algo.interfaces.Operatable;
import dev.austinzhu.algo.interfaces.Searchable;

public sealed interface Heap<T extends Comparable<T>>
        extends Operatable<T>, Searchable<T>
        permits BinaryHeap {
}