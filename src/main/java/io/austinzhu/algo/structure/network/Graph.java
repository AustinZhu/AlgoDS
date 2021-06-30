package io.austinzhu.algo.structure.network;

public sealed interface Graph<T> permits MatrixGraph {

    Integer[] getNeighbors(int v);

    void addEdge(int v1, int v2);

    void addVertex(T element, int id);
}
