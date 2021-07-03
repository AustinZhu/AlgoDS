package io.austinzhu.algo.structure.array;

import io.austinzhu.algo.interfaces.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * StaticArray is a linear data structure containing a collection of elements.
 *
 * @author Austin Zhu
 */
public sealed class Array<T extends Comparable<T>>
        implements Operatable<T>, Searchable<T>, Sortable
        permits Matrix {

    final int capacity;

    T[] data;

    int lowerBound;

    int upperBound;

    int length;

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        this.length = 0;
        this.lowerBound = 0;
        this.upperBound = 0;
        this.data = (T[]) new Comparable[capacity];
        this.capacity = capacity;
    }

    /**
     * Initialize a random integer array with size less than 20 and max element value less than 100
     *
     * @return an array of random integers
     */
    public static Array<Integer> init(int size, int bound, Random random) {
        if (size < 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        var capacity = random.nextInt(size + 1);
        Array<Integer> integerArray = new Array<>(capacity);
        for (var i = 0; i < capacity; i++) {
            integerArray.append(random.nextInt(bound));
        }
        return integerArray;
    }

    public static <T extends Comparable<T>> Array<T> merge(Array<T> a, Array<T> b) {
        int newLength = a.length + b.length;
        Array<T> newArray = new Array<>(newLength);
        if (a.length >= 0) {
            System.arraycopy(a.data, a.lowerBound, newArray.data, 0, a.length);
            System.arraycopy(b.data, b.lowerBound, newArray.data, a.length, b.length);
        }
        newArray.setUpperBound(newLength);
        return newArray;
    }

    public static <T extends Comparable<T>> Array<T> fromArray(T[] array) {
        Array<T> newArray = new Array<>(array.length);
        System.arraycopy(array, 0, newArray.data, 0, array.length);
        return newArray;
    }

    public static <T extends Comparable<T>> Array<T> slice(Array<T> arr, int start, int end) {
        if (start > arr.upperBound || end < arr.lowerBound || start > end) {
            throw new IllegalArgumentException("Wrong bound");
        }
        int newLength = end - start;
        Array<T> slice = new Array<>(newLength);
        System.arraycopy(arr.data, arr.lowerBound + start, slice.data, 0, newLength);
        slice.setUpperBound(newLength);
        return slice;
    }

    @SafeVarargs
    @Override
    public final void fill(T... elements) {
        for (T e : elements) {
            try {
                append(e);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.length = 0;
        this.lowerBound = 0;
        this.upperBound = 0;
        this.data = (T[]) new Comparable[capacity];
    }

    @Override
    public void set(int idx, T object) throws IndexOutOfBoundsException {
        if (idx >= 0 && idx < length) {
            data[lowerBound + idx] = object;
        } else {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
    }

    @Override
    public T get(int idx) throws IndexOutOfBoundsException {
        if (idx >= 0 && idx < length) {
            return data[lowerBound + idx];
        }
        throw new IndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public void insert(int idx, T object) throws IndexOutOfBoundsException {

    }

    @Override
    public T delete(int idx) throws IndexOutOfBoundsException {
        if (idx >= lowerBound && idx < upperBound) {
            T del = get(idx);
            set(idx, null);
            return del;
        }
        throw new IndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public void append(T element) throws IndexOutOfBoundsException {
        if (upperBound >= capacity) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        setUpperBound(upperBound + 1);
        set(length - 1, element);
    }

    @Override
    public T eject() throws IndexOutOfBoundsException {
        if (length <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T del = get(length - 1);
        set(length - 1, null);
        setUpperBound(length - 1);
        return del;
    }

    @Override
    public void prepend(T element) throws IndexOutOfBoundsException {

    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void sort() {
        try {
            sort(SortingAlgorithm.QUICK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(int start, int end) {
        if (start < lowerBound || end > upperBound || start > end) {
            throw new IllegalArgumentException("Wrong bound");
        }
        quickSort(start, end);
    }

    /**
     * Sort the array using the specified sorting algorithm
     *
     * @param sa name of the sorting algorithm being used
     */
    @Override
    @Algorithm("sort")
    public void sort(SortingAlgorithm sa) throws NoSuchAlgorithmException {
        sort(lowerBound, upperBound, sa);
    }

    @Override
    public void sort(int start, int end, SortingAlgorithm sa) throws NoSuchAlgorithmException {
        if (start < lowerBound || end > upperBound || start > end) {
            throw new IllegalArgumentException("Wrong bound");
        }
        switch (sa) {
            case BUBBLE -> bubbleSort(start, end);
            case MERGE -> mergeSort(start, end);
            case QUICK -> quickSort(start, end);
            case INSERTION -> insertionSort(start, end);
            case SELECTION -> selectionSort(start, end);
            case RADIX -> radixSort(start, end, 2);
            case HEAP -> heapSort(start, end);
            case COUNTING -> countingSort(start, end);
            case BUCKET -> bucketSort(start, end, 10);
            case SHELL -> shellSort(start, end);
            case SLEEP -> sleepSort(start, end);
            default -> throw new NoSuchAlgorithmException("No such algorithm");
        }
    }

    @Override
    public int search(T element) {
        try {
            return search(element, SearchingAlgorithm.LINEAR);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int search(T element, int start, int end) {
        try {
            return search(element, start, end, SearchingAlgorithm.LINEAR);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    @Algorithm("search")
    public int search(T element, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        return search(element, lowerBound, upperBound, sa);
    }

    @Override
    public int search(T element, int start, int end, SearchingAlgorithm sa) throws NoSuchAlgorithmException {
        if (start < lowerBound || end > upperBound || start > end) {
            throw new IllegalArgumentException("Wrong bound");
        }
        switch (sa) {
            case BINARY -> {
                return binarySearch(start, end, element);
            }
            case LINEAR -> {
                return linearSearch(start, end, element);
            }
            case JUMP -> {
                return jumpSearch(start, end, element);
            }
            default -> throw new NoSuchAlgorithmException("No such algorithm");
        }
    }

    @Override
    public boolean exist(T element) {
        try {
            return search(element, SearchingAlgorithm.BINARY) >= 0;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void slice(int start, int end) {
        setLowerBound(start);
        setUpperBound(end);
    }

    public void reverse() {
        reverse(0, length);
    }

    public void reverse(int start, int end) {
        for (int i = 0; i < (end + start) / 2; i++) {
            swap(i, (end + start) - i - 1);
        }
    }

    public void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    public int maximum() {
        return maximum(lowerBound, upperBound);
    }

    public int maximum(int lower, int upper) {
        if (lower < lowerBound || upper > upperBound || lower > upper) {
            throw new IndexOutOfBoundsException("Wrong bound");
        }
        int max = lower;
        for (int i = lower; i < upper; i++) {
            if (get(i).compareTo(get(max)) > 0) {
                max = i;
            }
        }
        return max;
    }

    public int minimum() {
        return minimum(lowerBound, upperBound);
    }

    public int minimum(int lower, int upper) {
        if (lower < lowerBound || upper > upperBound || lower > upper) {
            throw new IndexOutOfBoundsException("Wrong bound");
        }
        int min = lower;
        for (int i = lower; i < upper; i++) {
            if (get(i).compareTo(get(min)) < 0) {
                min = i;
            }
        }
        return min;
    }

    public T[] toArray() {
        return Arrays.copyOfRange(data, lowerBound, upperBound);
    }

    private int binarySearch(int lower, int upper, T element) {
        if (lower < upper) {
            int mid = (lower + upper) / 2;
            if (get(mid).equals(element)) {
                return mid;
            }
            if (get(mid).compareTo(element) > 0) {
                return binarySearch(lower, mid, element);
            }
            return binarySearch(mid + 1, upper, element);
        }
        return -1;
    }

    private int linearSearch(int lower, int upper, T element) {
        for (int i = lower; i < upper; i++) {
            if (get(i).equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private int jumpSearch(int lower, int upper, T element) {
        return -1;
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * Shell sort is a stable, in-place comparison sorting algorithm.
     * <p>
     * 1. Insertion sort every n elements
     * 2. Repeat 1. for next n in the geometric series with ratio 1/2
     * <p>
     * - performance of shell sort depends on the selection of interval series
     * Best time: O(n log n)
     * Worst time: O(n ^ 2) (for geometric series with ratio 1/2)
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
     * Bucket sort is a stable, distribution sorting algorithm.
     * <p>
     * 1. Create an array of buckets, each bucket holds elements that fall in some continuous, disjoint intervals.
     * 2. Put elements into their corresponding buckets.
     * 3. Sort each bucket using some sorting algorithm.
     * 4. Flatten the sorted bucket array(concat)
     * <p>
     * - select a interval so that elements are uniformly distributed to buckets
     * Average time: O(n + n ^ 2 / k + k)
     * Best time: O(n ^ 2)
     * Worsrt time: O(n * k)
     *
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     */
    @SuppressWarnings("unchecked")
    private void bucketSort(int lower, int upper, int n) {
        if (this.length <= 1 || n <= 0) {
            return;
        }
        double max = get(maximum(lower, upper)).hashCode();
        double min = get(minimum(lower, upper)).hashCode();
        int range = (int) Math.ceil((max - min) / n);
        // all elements are equal
        if (range == 0) {
            return;
        }
        ArrayList<T>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }
        // put elements into buckets
        for (int i = lower; i < upper; i++) {
            // bucket id is the tens digit here
            int bucketId = (int) ((get(i).hashCode() - min) / range);
            var bucket = buckets[bucketId == 0 ? 0 : bucketId - 1];
            bucket.add(get(i));
        }
        // flatten the array of buckets
        int k = lower;
        for (int i = 0; i < n; i++) {
            var bucket = buckets[i];
            Collections.sort(bucket);
            for (T t : bucket) {
                set(k, t);
                k++;
            }
        }

    }

    /**
     * description Counting sort is a stable, distribution sorting algorithm.
     * <p>
     * 1. Determine the range of values, initialize an array to count the frequency of each elements
     * 2. Traverse the array, add 1 to the counter of the element in the counting array
     * 3. Calculate the cumulative counts, this helps to build the sorted array
     * 4. Build the sorted array
     * <p>
     * - uses extra space to achieve lower time complexity
     * - element values are used as index in counting
     * - cumulative counts transform the frequency information to position information (costs k time)
     * Worst time: O(n + k)
     *
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     */
    private void countingSort(int lower, int upper) {
        TreeMap<T, Integer> counts = new TreeMap<>();
        for (int i = lower; i < upper; i++) {
            T key = get(i);
            if (counts.containsKey(key)) {
                counts.replace(key, counts.get(key) + 1);
            } else {
                counts.put(key, 1);
            }
        }
        int i = 0;
        for (Map.Entry<T, Integer> entry : counts.entrySet()) {
            T key = entry.getKey();
            Integer currentCount = entry.getValue();
            for (int j = 0; j < currentCount; j++, i++) {
                data[i] = key;
            }
        }
    }

    /**
     * Heap sort is an unstable, in-place comparison sorting algorithm
     * <p>
     * 1.[O(n)] Build a min heap out of the array
     * 2.[O(n log n)] adjust the heap for last len - i elements
     * 3. repeat 2. until no elements left to be heapified
     * <p>
     * - each iteration puts one element into its correct position, so the last i elements are sorted
     * Best time: O(n log n)
     * WorstRime: O(n log n)
     *
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     */
    private void heapSort(int lower, int upper) {
        heapify(lower, upper);
        for (int i = upper - 1; i >= lower; i--) {
            // swap the max and the last node
            swap(i, lower);
            // restore heap property
            int parent = lower;
            int child = lower + 1;
            while (child < i) {
                if (child + 1 < i && get(child).compareTo(get(child + 1)) < 0) {
                    child++;
                }
                if (get(parent).compareTo(get(child)) < 0) {
                    swap(parent, child);
                    parent = child;
                    child = 2 * child - lower + 1;
                } else {
                    break;
                }
            }
        }
    }

    private void heapify(int lower, int upper) {
        // build a max-heap for each parent node
        for (int i = (upper + lower) / 2 - 1; i >= 0; i--) {
            int parent = i;
            int child = 2 * i - lower + 1;
            while (child < upper) {
                if (child + 1 < upper && get(child).compareTo(get(child + 1)) < 0) {
                    child++;
                }
                if (get(parent).compareTo(get(child)) < 0) {
                    swap(child, parent);
                    parent = child;
                    child = 2 * child - lower + 1;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * @param lower  lower bound (inclusive)
     * @param upper  upper bound (exclusive)
     * @param digits maximum digits among the elements
     * Radix Sort is a stable, distribution sorting algorithm
     * <p>
     * 1. For each digits, perform a counting sort
     * <p>
     * - value at digit n of integer a is calculated by $$\frac{a}{10^n} \mod 10$$
     * @worstTime O(w * n)
     */
    @SuppressWarnings("unchecked")
    private void radixSort(int lower, int upper, int digits) {
        for (int j = 0; j < digits; j++) {
            int exp = (int) Math.pow(10, j);
            Integer[] output = new Integer[length];
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
            fromArray((T[]) output);
        }
    }

    /**
     * @param lower
     * @param upper
     * @description Selection sort is a stable, in-place comparison sorting algorithm.
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

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Insertion sort is a stable, in-place comparison soring algorithm.
     * <p>
     * 1. Get the next element
     * 2. Find the place to insert it by comparing to and moving the previous sorted elements one by one
     * 3. Insert the element and repeat 1., 2. for n times.
     * <p>
     * - each iteration puts one element into its correct position, so the first i elements are sorted
     */
    private void insertionSort(int lower, int upper) {
        for (int i = lower; i < upper; i++) {
            T current = get(i);
            int j = i - 1;
            // if the element is smaller than the previous one(s)
            while (j >= lower && current.compareTo(get(j)) < 0) {
                // move to reserve a spare position for the coming element
                set(j + 1, get(j));
                j--;
            }
            set(j + 1, current);
        }
    }

    /**
     * @param lower lower bound (inclusive)
     * @param upper upper bound (exclusive)
     * @description Quick sort is an unstable, in-place comparison sorting algorithm.
     * <p>
     * 1. Initialize left and right pointers, and calculate the pivot
     * 2. Increment left pointer and decrement right pointer to find any inverted pair, if any, swap them and continue
     * 3. When two pointers surpass each other, bipartite the array there into [lower, right + 1) and [right, upper)
     * 4. Quick sort the subarray(s) with size larger than 1
     * <p>
     * - terminates when subarray has length 1 or 0
     * - partition finishes when left pointer and right pointer cross
     * - partition 1. after pointers meet at pivot: [<pivot (pr), pivot,(pl) >pivot] or 2. [<=pivot (pr), (pl) >=pivot]
     * - if any element equals to the pivot, stop moving pointers and swap
     * - pointers must be updated after a swap, or the loop may never end
     * @bestTime O(n log n)
     * @avgTime O(n log n)
     * @worstTime O(n ^ 2)
     */
    private void quickSort(int lower, int upper) {
        // termination condition
        if (upper - lower < 2) {
            return;
        }
        // initialize left and right pointers
        int leftPointer = lower;
        int rightPointer = upper - 1;
        // select a random pivot
        T pivot = get(new Random().nextInt(upper - lower) + lower);
        // find the partition point
        while (leftPointer <= rightPointer) {
            // find any element from left that is larger than the pivot
            while (get(leftPointer).compareTo(pivot) < 0) {
                leftPointer++;
            }
            // find any element from right that is smaller than the pivot
            while (get(rightPointer).compareTo(pivot) > 0) {
                rightPointer--;
            }
            // swap the inverted pair and increment the pointers to continue
            if (leftPointer <= rightPointer) {
                swap(leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        quickSort(lower, leftPointer);
        quickSort(leftPointer, upper);
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
     * - if in some iteration no element is swapped, then the array is sorted
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
                if (get(j).compareTo(get(j + 1)) > 0) {
                    swap(j, j + 1);
                    sorted = false;
                }
            }
        }
    }

    private void sleepSort(int lower, int upper) {
        final var doneSignal = new CountDownLatch(length);
        Array<T> newArr = new Array<>(data.length);
        var ts = new Thread[length];
        for (int i = lower; i < upper; i++) {
            final T elem = get(i);
            var t = new Thread(() -> {
                doneSignal.countDown();
                try {
                    doneSignal.await();
                    Thread.sleep(elem.hashCode() * 500L);
                    newArr.append(elem);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });
            ts[i] = t;
            t.start();
        }
        for (var t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fromArray(newArr.data);
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
        this.length = upperBound - lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
        this.length = upperBound - lowerBound;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}