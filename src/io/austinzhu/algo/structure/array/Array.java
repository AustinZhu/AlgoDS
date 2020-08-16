package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Random;

/**
 * @author austi
 */
public class Array<T extends Comparable<T>> extends BaseArray<T> {

    public Array(int capacity) {
        super(capacity);
    }

    public static Array<Integer> init() {
        Random random = new Random();
        int capacity = random.nextInt(20);
        Array<Integer> integerArray = new Array<>(capacity);
        for (int i = 0; i < capacity; i++) {
            integerArray.append(random.nextInt(100));
        }
        return integerArray;
    }

    @Override
    @Algorithm("search")
    public boolean search(T element, SearchingAlgorithm sa) {
        return false;
    }

    @Override
    @Algorithm("sort")
    public void sort(SortingAlgorithm sa) {
        int lower = getLowerBound();
        int upper = getUpperBound();
        switch (sa) {
            case BUBBLE -> bubbleSort(lower, upper);
            case MERGE -> mergeSort(lower, upper);
            case QUICK -> quickSort(lower, upper);
            case INSERTION -> insertionSort(lower, upper);
            case SELECTION -> selectionSort(lower, upper);
            case RADIX -> radixSort();
            case HEAP -> heapSort();
            case COUNTING -> countingSort();
            case BUCKET -> bucketSort();
            case SHELL -> shellSort(lower, upper);
            default -> throw new NoSuchAlgorithmException("No such algorithm");
        }
    }

    /**
     * Shell sort is a variant of insertion sort.
     * 1. Insertion sort every n element
     * 2. Repeat 1. for next n in the geometric series with ratio 1/2
     *
     * @param lower
     * @param upper
     * @bestTime O(n log n)
     * @worstTime O(n ^ 2) (for geometric series with ratio 1/2)
     */
    private void shellSort(int lower, int upper) {
        for (int interval = (upper - lower) / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < upper; i++) {
                T temp = get(i);
                int j = i;
                while (j >= interval && get(j - interval).compareTo(temp) > 0) {
                    set(j, get(j - interval));
                    j -= interval;
                }
                set(j, temp);
            }
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

    private void selectionSort(int lower, int upper) {
        for (int i = lower; i < upper - 1; i++) {
            T minVal = get(i);
            int minPos = i;
            for (int j = i + 1; j < upper; j++) {
                if (get(j).compareTo(minVal) < 0) {
                    minPos = j;
                    minVal = get(j);
                }
            }
            swap(i, minPos);
        }
    }

    private void insertionSort(int lower, int upper) {
        for (int i = lower; i < upper; i++) {
            T current = get(i);
            int j = i - 1;
            while (current.compareTo(get(j)) < 0 && j >= 0) {
                set(j + 1, get(j));
                j--;
            }
            set(j + 1, current);
        }
    }

    /**
     * Quick sort is an unstable, comparison-based, in-place sorting algorithm
     * <p>
     * 1. Initialize left and right pointer, calculate the pivot
     * 2. Increment left pointer and decrement right pointer to find any inverted pairs, if any, swap them and continue
     * 3. When they surpass each other, bipartite the array there into [lower, right + 1) and [right, upper)
     * 4. Quick sort the subarray(s) with size larger than 1
     * <p>
     * - lower bound must be smaller than the upper bound
     * - partition finishes when left pointer and right pointer invert
     * - if any element equals to the pivot, stop moving pointers and swap
     * - pointers must be updated after a swap, or the loop may never end
     * - check if a subarray has size larger than 1, or empty subarrays will smash your call stack
     *
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @bestTime O(n log n)
     * @worstTime O(n ^ 2)
     */
    private void quickSort(int lower, int upper) {
        int leftPointer = lower;
        int rightPointer = upper - 1;
        T pivot = get(new Random().nextInt(upper - lower) + lower);
        while (leftPointer <= rightPointer) {
            while (get(leftPointer).compareTo(pivot) < 0) {
                leftPointer++;
            }
            while (get(rightPointer).compareTo(pivot) > 0) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) {
                swap(leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        if (lower < rightPointer) {
            quickSort(lower, rightPointer + 1);
        }
        if (upper > leftPointer) {
            quickSort(leftPointer, upper);
        }
    }

    /**
     * Merge sort is a stable, comparison based sorting algorithm.
     * <p>
     * 1. equally bipartite the array into two subarrays
     * 2. repeat 1. until the array is fully divided into singletons
     * 3. copy the sorted subarrays to a left array and a right array
     * 4. merge two sorted arrays
     * 5. repeat 3., 4. on other sorted subarrays
     *
     * @param lower
     * @param upper
     */
    @SuppressWarnings("unchecked")
    private void mergeSort(int lower, int upper) {
        // if there's only one element in the array, it's sorted
        if (upper - lower <= 1) {
            return;
        }
        int mid = (lower + upper) / 2;
        // sort left subarray
        mergeSort(lower, mid);
        // sort right subarray
        mergeSort(mid, upper);
        int leftLength = mid - lower;
        int rightLength = upper - mid;
        // create temp array for comparison and merge
        T[] leftArray = (T[]) new Comparable[leftLength];
        T[] rightArray = (T[]) new Comparable[rightLength];
        // fill the temp arrays
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = get(lower + i);
        }
        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = get(mid + i);
        }
        // merge array
        int leftId = 0, rightId = 0, arrayId = lower;
        while (leftId < leftLength && rightId < rightLength) {
            if (leftArray[leftId].compareTo(rightArray[rightId]) < 0) {
                set(arrayId, leftArray[leftId]);
                leftId++;
            } else {
                set(arrayId, rightArray[rightId]);
                rightId++;
            }
            arrayId++;
        }
        while (leftId < leftLength) {
            set(arrayId, leftArray[leftId]);
            leftId++;
            arrayId++;
        }
        while (rightId < rightLength) {
            set(arrayId, rightArray[rightId]);
            rightId++;
            arrayId++;
        }
    }

    private void bubbleSort(int lower, int upper) {
        for (int i = lower; i < upper; i++) {
            for (int j = 0; j < upper - i - 1; j++) {
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