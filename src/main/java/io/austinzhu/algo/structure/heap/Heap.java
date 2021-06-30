package io.austinzhu.algo.structure.heap;

import io.austinzhu.algo.interfaces.Interactable;
import io.austinzhu.algo.interfaces.Searchable;

public sealed interface Heap<T extends Comparable<T>>
        extends Interactable<T>, Searchable<T>
        permits BinaryHeap {
}