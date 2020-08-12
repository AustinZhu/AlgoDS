package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.IndexOutOfBoundsException;
import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

public class Array<T extends Comparable<T>> extends BaseArray<T> {

    public Array(int capacity) {
        super(capacity);
    }

    @Override
    public boolean search(T element, SearchingAlgorithm sa) {
        return false;
    }

    @Override
    public void sort(SortingAlgorithm sa) {
        switch (sa) {
            case BUBBLE -> bubbleSort();
            case MERGE -> mergeSort();
            case QUICK -> quickSort();
            case INSERTION -> insertionSort();
            case SELECTION -> selectionSort();
            case RADIX -> radixSort();
            case HEAP -> heapSort();
            case COUNTING -> countingSort();
            case BUCKET -> bucketSort();
            default -> throw new NoSuchAlgorithmException("No such algorithm");
        }
    }

    private void bucketSort() {
    }

    private void countingSort() {
    }

    private void heapSort() {
    }

    private void radixSort() {
    }

    private void selectionSort() {
    }

    private void insertionSort() {
    }

    private void quickSort() {
    }

    private void mergeSort() {
    }

    private void bubbleSort() {
        for (int i = getLowerBound(); i < getUpperBound(); i++) {
            for (int j = 0; j < getUpperBound() - i - 1; j++) {
                if (getData()[j].compareTo(getData()[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
}
