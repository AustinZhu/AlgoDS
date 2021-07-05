package io.austinzhu.algo.structure.network;

import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public final class MatrixGraph<T> implements Graph<T> {

    private final int[][] edges;

    private final T[] vertices;

    private final boolean isDirected;

    @SuppressWarnings("unchecked")
    public MatrixGraph(int capacity, boolean isDirected) {
        this.edges = new int[capacity][capacity];
        this.vertices = (T[]) new Object[capacity];
        this.isDirected = isDirected;
    }

    public static MatrixGraph<Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        MatrixGraph<Integer> graph = new MatrixGraph<>(capacity, true);
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

    @Override
    public void addVertex(int id, T element) throws IndexOutOfBoundsException {
        vertices[id] = element;
    }

    @Override
    public List<Integer> getNeighbors(int vid) {
        List<Integer> neighbors = new ArrayList<>();
        for (var i = 0; i < vertices.length; i++) {
            if (edges[vid][i] != 0) {
                neighbors.add(i);
            }
        }
        if (isDirected) {
            return neighbors;
        }
        for (var i = 0; i < vertices.length; i++) {
            if (edges[i][vid] != 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    @Override
    public void addEdge(int v1, int v2) {
        edges[v1][v2] = 1;
    }

    @Override
    @Algorithm
    public List<Integer> searchPath(int v1, int v2, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        if (v1 >= vertices.length || v2 >= vertices.length || v1 < 0 || v2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        switch (sa) {
            case BFS -> {
                return bfs(v1, v2);
            }
            case DFS -> {
                return dfs(v1, v2, new ArrayList<>());
            }
            default -> throw new NoSuchAlgorithmException();
        }
    }

    public boolean existsPath(int v1, int v2) {
        return false;
    }

    private List<Integer> bfs(int v1, int v2) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(v1);
        queue.offer(path);
        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == v2) {
                return path;
            }
            var neighbors = getNeighbors(last);
            for (var v : neighbors) {
                if (!path.contains(v)) {
                    var newPath = new ArrayList<>(path);
                    newPath.add(v);
                    queue.offer(newPath);
                }
            }
        }
        return new ArrayList<>();
    }

    private List<Integer> dfs(int v1, int v2, List<Integer> path) {
        path.add(v1);
        var last = path.get(path.size() - 1);
        if (last == v2) {
            return path;
        }
        var neighbors = getNeighbors(last);
        for (var v : neighbors) {
            if (path.contains(v)) {
                continue;
            }
            List<Integer> newPath = dfs(v, v2, path);
            if (!newPath.isEmpty()) {
                return newPath;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < edges.length; i++) {
            for (var j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != 0) {
                    sb.append("v").append(i)
                            .append(" -> ")
                            .append("v").append(j)
                            .append("\n");
                }
            }
        }
        return sb.toString();
    }
}
