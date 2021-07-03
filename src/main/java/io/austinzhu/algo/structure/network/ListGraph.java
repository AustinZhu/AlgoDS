package io.austinzhu.algo.structure.network;

import java.util.ArrayList;

public final class ListGraph<T> implements Graph<T> {

    private final ArrayList<Integer>[] edges;

    private final T[] vertices;

    @SuppressWarnings("unchecked")
    public ListGraph(int capacity) {
        this.vertices = (T[]) new Object[capacity];
        this.edges = new ArrayList[capacity];
    }

    @Override
    public Integer[] getNeighbors(int v) {
        return new Integer[0];
    }

    @Override
    public void addEdge(int v1, int v2) {
        if (v1 >= vertices.length || v2 >= vertices.length || v1 < 0 || v2 < 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        edges[v1].add(v2);
    }

    @Override
    public void addVertex(int id, T element) {
        vertices[id] = element;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < edges.length; i++) {
            for (var e : edges[i]) {
                sb.append("v").append(i).append(" -> ").append("v").append(e).append("\n");
            }
        }
        return sb.toString();
    }
}
