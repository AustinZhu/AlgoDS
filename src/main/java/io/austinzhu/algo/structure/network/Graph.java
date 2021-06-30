package io.austinzhu.algo.structure.network;

public interface Graph<T> {

    Integer[] getNeighbors(int v);

    void addEdge(int v1, int v2);

    void addVertex(T element, int id);
}
