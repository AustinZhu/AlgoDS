package io.austinzhu.algo.structure.network;

public sealed interface Graph<T> permits MatrixGraph, ListGraph {

    Integer[] getNeighbors(int v);

    void addEdge(int v1, int v2);

    void addVertex(int id, T element);
}
