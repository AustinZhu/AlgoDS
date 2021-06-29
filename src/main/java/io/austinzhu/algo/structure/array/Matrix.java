package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Matrix<T extends Comparable<T>> extends Array<T> {

    private final int[] dimension;

    public static Matrix<Integer> init(int size, int bound, Random random) {
        var size1 = random.nextInt(size);
        var size2 = random.nextInt(size);
        var capacity = new int[]{size1, size2};
        Matrix<Integer> integerMatrix = new Matrix<>(capacity);
        for (var i = 0; i < size1 * size2; i++) {
            integerMatrix.append(random.nextInt(bound));
        }
        return integerMatrix;
    }

    public Matrix(int... capacity) {
        super(Arrays.stream(capacity).reduce(1, ((a, b) -> a * b)));
        this.dimension = capacity;
    }

    public void set(T object, int... id) throws IndexOutOfBoundsException {
        if (id.length != dimension.length) {
            throw new IndexOutOfBoundsException("Cannot index into matrix");
        }
        int loc = IntStream.range(0, 3).map(i -> dimension[i] * id[i]).sum();
        data[loc] = object;
    }

    public T get(int... id) throws IndexOutOfBoundsException {
        if (id.length != dimension.length) {
            throw new IndexOutOfBoundsException("Cannot index into matrix");
        }
        int loc = IntStream.range(0, 3).map(i -> dimension[i] * id[i]).sum();
        return data[loc];
    }

    @Override
    @Algorithm("search")
    public int search(T element, SearchingAlgorithm sa) {
        return super.search(element, sa);
    }

    @Override
    @Algorithm("sort")
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {
        super.sort(sa);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < dimension[0]; i++) {
            int start = i * dimension[1];
            for (int j = start; j < start + dimension[1]; j++) {
                sb.append("[").append(data[j]).append("] ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
