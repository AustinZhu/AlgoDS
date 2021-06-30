package io.austinzhu.algo.structure.network;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.Random;

public final class MatrixGraph<T> implements Graph<T> {

    private final boolean[][] adjMat;

    private final T[] vertices;

    @SuppressWarnings("unchecked")
    public MatrixGraph(int capacity) {
        this.adjMat = new boolean[capacity][capacity];
        this.vertices = (T[]) new Object[capacity];
    }

    public static MatrixGraph<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        MatrixGraph<Integer> graph = new MatrixGraph<>(capacity);
        for (var i = 0; i < capacity; i++) {
            graph.addVertex(random.nextInt(bound), i);
        }
        for (int i = 0; i < capacity; i++) {
            int v1 = random.nextInt(capacity);
            int v2 = random.nextInt(capacity);
            graph.addEdge(v1, v2);
        }
        return graph;
    }

    @Override
    public void addVertex(T element, int id) throws IndexOutOfBoundsException {
        vertices[id] = element;
    }

    @Override
    public Integer[] getNeighbors(int vid) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < adjMat.length; i++) {
            if (adjMat[vid][i]) {
                neighbors.add(i);
            }
        }
        return neighbors.toArray(new Integer[0]);
    }

    @Override
    public void addEdge(int v1, int v2) {
        adjMat[v1][v2] = true;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[i].length; j++) {
                if (adjMat[i][j]) {
                    sb.append("v").append(i).append(" -> ").append("v").append(j).append("\n");
                }
            }
        }
        return sb.toString();
    }
}