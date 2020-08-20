package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.exception.NoSuchAlgorithmException;
import io.austinzhu.algo.interfaces.Algorithm;
import io.austinzhu.algo.interfaces.SearchingAlgorithm;
import io.austinzhu.algo.interfaces.SortingAlgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Austin Zhu
 * @description Array is a linear data structure containing a collection of elements
 */
public class Array<T extends Comparable<T>> extends BaseArray<T> {

    public Array(int capacity) {
        super(capacity);
    }

    /**
     * @return an array of random integers
     * @description initialize a random integer array with size less than 20 and max element value less than 100
     */
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

    /**
     * @param sa name of the sorting algorithm being used
     * @description Sort the array using the specified sorting algorithm
     */
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
            case RADIX -> radixSort(lower, upper, 2);
            case HEAP -> heapSort(lower, upper);
            case COUNTING -> countingSort(lower, upper, 100);
            case BUCKET -> bucketSort(lower, upper);
            case SHELL -> shellSort(lower, upper);
            default -> throw new NoSuchAlgorithmException("No such algorithm");
        }
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Shell sort is a stable, in-place comparison sorting algorithm.
     * <p>
     * 1. Insertion sort every n elements
     * 2. Repeat 1. for next n in the geometric series with ratio 1/2
     * <p>
     * - performance of shell sort depends on the selection of interval series
     * @bestTime O(n log n)
     * @worstTime O(n ^ 2) (for geometric series with ratio 1/2)
     */
    private void shellSort(int lower, int upper) {
        int ratio = 2;
        // initial interval = len / 2
        for (int interval = (upper - lower) / ratio; interval > 0; interval /= ratio) {
            // insertion sort each n elements,
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

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Bucket sort is a stable distribution sorting algorithm.
     * <p>
     * 1. Create an array of buckets, each bucket holds elements that fall in some continuous, disjoint intervals.
     * 2. Put elements into their corresponding buckets.
     * 3. Sort each bucket using some sorting algorithm.
     * 4. Flatten the sorted bucket array(concat)
     * <p>
     * - select a interval so that elements are uniformly distributed to buckets
     * @avgTime O(n + n ^ 2 / k + k)
     * @worstTime O(n ^ 2)
     * @worstSpace O(n * k)
     */
    private void bucketSort(int lower, int upper) {
        if (this.getLength() <= 1) {
            return;
        }
        // size of the interval
        int size = 10;
        Array<T>[] buckets = new Array[size];
        // initialize buckets
        for (int i = 0; i < size; i++) {
            buckets[i] = new Array<>(getLength());
        }
        // put elements into buckets
        for (int i = lower; i < upper; i++) {
            // bucket id is the tens digit here
            int bucketId = get(i).hashCode() / size;
            buckets[bucketId].append(get(i));
        }
        // flatten the array of buckets
        Array<T> result = new Array<>(0);
        for (int i = 0; i < size; i++) {
            Array<T> bucket = buckets[i];
            bucket.insertionSort(bucket.getLowerBound(), bucket.getUpperBound());
            result.join(bucket);
        }
        replaceBy(result);
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @param range max element value
     * @description Counting sort is a stable, distribution sorting algorithm.
     * <p>
     * 1. Determine the range of values, initialize an array to count the frequency of each elements
     * 2. Traverse the array, add 1 to the counter of the element in the counting array
     * 3. Calculate the cumulative counts, this helps to build the sorted array
     * 4. Build the sorted array
     * <p>
     * - uses extra space to achieve lower time complexity
     * - element values are used as index in counting
     * - cumulative counts transform the frequency information to position information
     */
    @SuppressWarnings("unchecked")
    public void countingSort(int lower, int upper, int range) {
        Integer[] output = new Integer[getLength()];
        int[] counts = new int[range];
        for (int i = lower; i < upper; i++) {
            counts[get(i).hashCode()]++;
        }
        // cumulative counts indicates how many elements before the last occurrence of i in the sorted array
        for (int i = 1; i < range; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = upper - 1; i >= lower; i--) {
            int index = get(i).hashCode();
            output[counts[index] - 1] = index;
            counts[index]--;
        }
        setData((T[]) output);
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Heap sort is an unstable, in-place comparison sorting algorithm
     * <p>
     * 1. Build a max heap out of the array
     * 2. Swap the first element(max) with the last element
     * 3. adjust the heap for previous len - i elements
     * 4. repeat 2., 3. until no elements left to be heapified
     * <p>
     * - each iteration puts one element into its correct position, so the last i elements are sorted
     * @bestTime O(n log n)
     * @worstTime O(n log n)
     */
    private void heapSort(int lower, int upper) {
        for (int i = (lower + upper) / 2 - 1; i >= lower; i--) {
            heapify(getLength(), i);
        }
        for (int i = upper - 1; i > lower; i--) {
            swap(lower, i);
            heapify(i - lower, 0);
        }
    }

    @SuppressWarnings("unchecked")
    private void radixSort(int lower, int upper, int digits) {
        for (int j = 0; j < digits; j++) {
            int exp = (int) Math.pow(10, j);
            Integer[] output = new Integer[getLength()];
            int[] counts = new int[10];
            // counting sort
            for (int i = lower; i < upper; i++) {
                int index = get(i).hashCode();
                int valAtDigit = (index / exp) % 10;
                counts[valAtDigit]++;
            }
            for (int i = 1; i < 10; i++) {
                counts[i] += counts[i - 1];
            }
            for (int i = upper - 1; i >= lower; i--) {
                int index = get(i).hashCode();
                int valAtDigit = (index / exp) % 10;
                output[lower + counts[valAtDigit] - 1] = index;
                counts[valAtDigit]--;
            }
            setData((T[]) output);
        }
    }

    /**
     * @param lower
     * @param upper
     * @description Selection sort is a stable, in-place comparison algorithm.
     * <p>
     * 1. Assume the current element is the min element
     * 2. Find the minimum element in the rest of the unsorted array
     * 3. Swap the current element with the minimum one
     * 4. Repeat 1., 2., 3. for n times
     * <p>
     * - each iteration puts one element into its correct position, so the first i elements are sorted
     * @bestTime O(n ^ 2)
     * @avgTime O(n ^ 2)
     * @worstTime O(n ^ 2)
     */
    private void selectionSort(int lower, int upper) {
        for (int i = lower; i < upper; i++) {
            int minPos = i;
            T minVal = get(i);
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
            while (j >= 0 && current.compareTo(get(j)) < 0) {
                set(j + 1, get(j));
                j--;
            }
            set(j + 1, current);
        }
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Quick sort is an unstable, comparison-based, in-place sorting algorithm.
     * <p>
     * 1. Initialize left and right pointer, and calculate the pivot
     * 2. Increment left pointer and decrement right pointer to find any inverted pair, if any, swap them and continue
     * 3. When two pointers surpass each other, bipartite the array there into [lower, right + 1) and [right, upper)
     * 4. Quick sort the subarray(s) with size larger than 1
     * <p>
     * - lower bound must be smaller than the upper bound
     * - partition finishes when left pointer and right pointer invert
     * - if any element equals to the pivot, stop moving pointers and swap
     * - pointers must be updated after a swap, or the loop may never end
     * - check if a subarray has size larger than 1, or empty subarrays will smash your call stack
     * @bestTime O(n log n)
     * @avgTime O(n log n)
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
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Merge sort is a stable, comparison based sorting algorithm.
     * <p>
     * 1. equally bipartite the array into two subarrays
     * 2. repeat 1. until the array is fully divided into singletons
     * 3. copy the sorted subarrays to a left array and a right array
     * 4. merge two sorted arrays
     * 5. repeat 3., 4. on other sorted subarrays
     * <p>
     * - termination condition: a singleton array or an empty array
     * - after comparison, there must be one or more elements left in either left or right subarray
     * @bestTime O(n log n)
     * @avgTime O(n log n)
     * @worstTime O(n log n)
     * @worstSpace O(n)
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

        // merge
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
        // initialize three pointers
        int leftId = 0, rightId = 0, arrayId = lower;
        // merge left and right array
        while (leftId < leftLength && rightId < rightLength) {
            // since left and right are sorted, select left element if it's smaller, or select right element
            if (leftArray[leftId].compareTo(rightArray[rightId]) < 0) {
                set(arrayId, leftArray[leftId]);
                leftId++;
            } else {
                set(arrayId, rightArray[rightId]);
                rightId++;
            }
            arrayId++;
        }
        // add the remaining elements in either subarray
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

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Bubble sort is a stable, in-place comparison sorting algorithm
     * <p>
     * 1. compare the current element to the next one, if they are inverted, swap them
     * 2. repeat 1. for n times
     * <p>
     * - each iteration puts one element into its correct position, so the last i elements are sorted
     * - if in one of the iterations, no element is swapped, then the array is sorted
     * @bestTime O(n)
     * @avgTime O(n ^ 2)
     * @worstTime O(n ^ 2)
     */
    private void bubbleSort(int lower, int upper) {
        boolean sorted = true;
        for (int i = lower; i < upper || !sorted; i++) {
            // assume sorted at the beginning of each iteration
            sorted = true;
            for (int j = 0; j < upper - i - 1; j++) {
                // compare with the next element
                if (getData()[j].compareTo(getData()[j + 1]) > 0) {
                    swap(j, j + 1);
                    sorted = false;
                }
            }
        }
    }

    private void heapify(int len, int rootId) {
        int max = rootId;
        int leftId = 2 * rootId + 1;
        int rightId = 2 * rootId + 2;
        if (leftId < len && get(max).compareTo(get(leftId)) < 0) {
            max = leftId;
        }
        if (rightId < len && get(max).compareTo(get(rightId)) < 0) {
            max = rightId;
        }
        // if max == root, loops forever
        if (max != rootId) {
            swap(rootId, max);
            heapify(len, max);
        }
    }

    private void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
}