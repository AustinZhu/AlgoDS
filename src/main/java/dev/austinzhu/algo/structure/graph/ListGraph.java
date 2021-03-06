package dev.austinzhu.algo.structure.graph;

import dev.austinzhu.algo.interfaces.SearchingAlgorithm;
import dev.austinzhu.algo.structure.Graph;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ListGraph<T> implements Graph<T> {

    private final ArrayList<Integer>[] edges;

    private final T[] vertices;

    private final boolean isDirected;

    public static ListGraph<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        ListGraph<Integer> graph = new ListGraph<>(capacity, true);
        for (var i = 0; i < capacity; i++) {
            graph.addVertex(i, random.nextInt(bound));
        }
        for (var i = 0; i < capacity; i++) {
            var v1 = random.nextInt(capacity);
            var v2 = random.nextInt(capacity);
            graph.addEdge(v1, v2);
        }
        return graph;
    }

    @SuppressWarnings("unchecked")
    public ListGraph(int capacity, boolean isDirected) {
        this.vertices = (T[]) new Object[capacity];
        this.edges = new ArrayList[capacity];
        this.isDirected = isDirected;
    }

    @Override
    public List<Integer> getNeighbors(int v) {
        return edges[v];
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
        edges[id] = new ArrayList<>();
        vertices[id] = element;
    }

    @Override
    public List<Integer> searchPath(int v1, int v2, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return null;
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
