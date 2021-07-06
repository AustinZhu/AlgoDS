package dev.austinzhu.algo.structure;

import dev.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface Graph<T> {

    List<Integer> getNeighbors(int v);

    void addEdge(int v1, int v2);

    void addVertex(int id, T element);

    List<Integer> searchPath(int v1, int v2, SearchingAlgorithm sa) throws NoSuchAlgorithmException;
}
