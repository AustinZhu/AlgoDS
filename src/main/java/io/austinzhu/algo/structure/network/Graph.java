package io.austinzhu.algo.structure.network;

import java.util.List;

public sealed interface Graph<T> permits MatrixGraph, ListGraph {

    List<Integer> getNeighbors(int v);

    void addEdge(int v1, int v2);

    void addVertex(int id, T element);
}
