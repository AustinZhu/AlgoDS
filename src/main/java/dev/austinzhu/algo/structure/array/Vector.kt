package dev.austinzhu.algo.structure.array

import dev.austinzhu.algo.interfaces.*
import dev.austinzhu.algo.structure.Sequence
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.security.NoSuchAlgorithmException
import kotlin.Array
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

open class Vector<T : Comparable<T>>(val capacity: Int) : Operatable<T>, Searchable<T>, Sortable, Sequence<T> {

    @Suppress("UNCHECKED_CAST")
    val data = arrayOfNulls<Comparable<T>>(capacity) as Array<T?>

    var lowerBound = 0
        set(lower) {
            field = lower
            if (lower < 0) throw IllegalArgumentException("Cannot set start bound below 0")
            length = upperBound - lower
        }

    var upperBound = 0
        set(upper) {
            field = upper
            if (upper > data.size) throw IllegalArgumentException("Cannot set end bound beyond capacity")
            length = upper - lowerBound
        }

    var length = 0

    var isSorted = false

    companion object {
        fun init(size: Int, bound: Int, random: Random): Vector<Int> {
            if (size < 0) {
                throw IllegalArgumentException("Size must be a natural number")
            }
            val capacity = random.nextInt(size + 1)
            val intVector = Vector<Int>(capacity)
            for (i in 0 until capacity) {
                intVector.append(random.nextInt(bound))
            }
            return intVector
        }

        fun <T : Comparable<T>> merge(a: Vector<T>, b: Vector<T>): Vector<T> {
            val newLength = a.length + b.length
            val newVector = Vector<T>(newLength)
            a.data.copyInto(newVector.data)
            b.data.copyInto(newVector.data, a.length)
            newVector.upperBound = newLength
            return newVector
        }

        fun <T : Comparable<T>> fromArray(arr: Array<T>): Vector<T> {
            val newArr = Vector<T>(arr.size)
            arr.copyInto(newArr.data)
            return newArr
        }

        fun <T : Comparable<T>> slice(arr: Vector<T>, start: Int, end: Int): Vector<T> {
            if (start < arr.lowerBound || end > arr.upperBound || start > end) {
                throw IllegalArgumentException("Invalid bound")
            }
            val slice = Vector<T>(end - start)
            arr.data.copyInto(slice.data, 0, start + arr.lowerBound, end + arr.lowerBound)
            slice.upperBound = end - start
            return slice
        }
    }

    override fun fill(vararg elements: T) {
        for (e in elements) {
            append(e)
        }
    }

    override fun clear() {
        length = 0
        lowerBound = 0
        upperBound = 0
        data.fill(null)
    }

    override fun set(idx: Int, value: T?) {
        if (idx in 0 until length) {
            data[lowerBound + idx] = value
        } else {
            throw IndexOutOfBoundsException(idx)
        }
    }

    override fun get(idx: Int): T {
        if (idx in 0 until length) {
            return data[lowerBound + idx]!!
        }
        throw IndexOutOfBoundsException(idx)
    }

    override fun insert(idx: Int, value: T) {
        if (lowerBound > 0) {
            data.copyInto(data, lowerBound - 1, lowerBound, idx + 1)
            lowerBound--
        } else if (upperBound < data.size) {
            data.copyInto(data, idx + 1, idx, upperBound)
            upperBound++
        } else {
            throw IndexOutOfBoundsException("Vector is full")
        }
        set(idx, value)
    }

    override fun delete(idx: Int): T {
        val del = get(idx)
        data.copyInto(data, idx, idx + 1, upperBound)
        upperBound--
        return del
    }

    override fun append(element: T) {
        if (lowerBound > 0 && upperBound == data.size) {
            data.copyInto(data, 0, lowerBound, upperBound)
            lowerBound = 0
            upperBound = length
        }
        upperBound++
        set(length - 1, element)
    }

    override fun eject(): T {
        val del = get(length - 1)
        upperBound--
        return del
    }

    override fun prepend(element: T) {
        if (lowerBound > 0) {
            lowerBound--
        } else if (upperBound < data.size) {
            data.copyInto(data, lowerBound + 1, lowerBound, upperBound)
            upperBound++
        } else {
            throw IndexOutOfBoundsException("Vector is full")
        }
        set(lowerBound, element)
    }

    override fun pop(): T {
        val del = get(lowerBound)
        lowerBound++
        return del
    }

    override fun search(element: T): Int {
        return search(element, lowerBound, upperBound)
    }

    override fun search(element: T, start: Int, end: Int): Int {
        if (isSorted) {
            return search(element, lowerBound, upperBound, SearchingAlgorithm.BINARY)
        }
        return search(element, lowerBound, upperBound, SearchingAlgorithm.LINEAR)
    }

    override fun search(element: T, sa: SearchingAlgorithm): Int {
        return search(element, lowerBound, upperBound, sa)
    }

    override fun search(element: T, start: Int, end: Int, sa: SearchingAlgorithm): Int {
        return when (sa) {
            SearchingAlgorithm.LINEAR -> linearSearch(element, start, end)
            SearchingAlgorithm.BINARY -> binarySearch(element, start, end)
            SearchingAlgorithm.JUMP -> jumpSearch(element, start, end)
            else -> throw NoSuchAlgorithmException(sa.name.lowercase() + " search is not supported")
        }
    }

    private fun linearSearch(element: T, start: Int, end: Int): Int {
        for (i in start until end) {
            if (get(i) == element) {
                return i
            }
        }
        return -1
    }

    private fun binarySearch(element: T, start: Int, end: Int): Int {
        if (start >= end) {
            return -1
        }
        val mid = (start + end) / 2
        if (get(mid) == element) {
            return mid
        }
        if (get(mid) > element) {
            return binarySearch(element, start, mid)
        }
        return binarySearch(element, mid, end)
    }

    private fun jumpSearch(element: T, start: Int, end: Int): Int {
        return -1
    }

    override fun exist(element: T): Boolean {
        return search(element) >= 0
    }

    override fun slice(start: Int, end: Int) {
        lowerBound = start
        upperBound = end
    }

    override fun reverse() {
        reverse(0, length)
    }

    override fun reverse(start: Int, end: Int) {
        for (i in 0 until (end + start) / 2) {
            swap(i, end + start - i - 1)
        }
    }

    open fun swap(i: Int, j: Int) {
        val temp = get(i)
        set(i, get(j))
        set(j, temp)
    }

    override fun max(): Int {
        return max(lowerBound, upperBound)
    }

    override fun max(start: Int, end: Int): Int {
        if (start < lowerBound || end > upperBound || start > end) {
            throw IndexOutOfBoundsException("Wrong bound")
        }
        var max = start
        for (i in start until end) {
            if (get(i) > get(max)) {
                max = i
            }
        }
        return max
    }

    override fun min(): Int {
        return min(lowerBound, upperBound)
    }

    override fun min(start: Int, end: Int): Int {
        if (start < lowerBound || end > upperBound || start > end) {
            throw IndexOutOfBoundsException("Wrong bound")
        }
        var min = start
        for (i in start until end) {
            if (get(i) < get(min)) {
                min = i
            }
        }
        return min
    }

    override fun toArray(): Array<T?> {
        return toArray(lowerBound, upperBound)
    }

    override fun toArray(start: Int, end: Int): Array<T?> {
        if (start < lowerBound || end > upperBound || start > end) {
            throw IndexOutOfBoundsException("Wrong bound")
        }
        return data.copyOfRange(start, end)
    }

    override fun sort() {
        sort(lowerBound, upperBound)
    }

    override fun sort(start: Int, end: Int) {
        sort(start, end, SortingAlgorithm.MERGE)
    }

    override fun sort(sa: SortingAlgorithm) {
        sort(lowerBound, upperBound, sa)
    }

    override fun sort(start: Int, end: Int, sa: SortingAlgorithm) {
        when (sa) {
            SortingAlgorithm.MERGE -> mergeSort(start, end)
            SortingAlgorithm.HEAP -> heapSort(start, end)
            SortingAlgorithm.BUBBLE -> bubbleSort(start, end)
            SortingAlgorithm.BUCKET -> bucketSort(start, end)
            SortingAlgorithm.INSERTION -> insertionSort(start, end)
            SortingAlgorithm.QUICK -> quickSort(start, end)
            SortingAlgorithm.COUNTING -> countingSort(start, end)
            SortingAlgorithm.RADIX -> radixSort(start, end)
            SortingAlgorithm.SLEEP -> sleepSort(start, end)
            SortingAlgorithm.SELECTION -> selectionSort(start, end)
            SortingAlgorithm.SHELL -> shellSort(start, end)
            SortingAlgorithm.TIM -> timSort(start, end)
            else -> throw NoSuchAlgorithmException(sa.name.lowercase() + " sort is not supported")
        }
        isSorted = true
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     *              Shell sort is a stable, in-place comparison sorting algorithm.
     *              <p>
     *              1. Insertion sort every n elements
     *              2. Repeat 1. for next n in the geometric series with ratio 1/2
     *              <p>
     *              - performance of shell sort depends on the selection of interval series
     *              Best time: O(n log n)
     *              Worst time: O(n ^ 2) (for geometric series with ratio 1/2)
     */
    private fun shellSort(start: Int, end: Int) {
        val ratio = 2
        // initial interval = len / 2
        var interval = (end - start) / ratio
        while (interval > 0) {
            // insertion sort each n elements,
            for (i in interval until end) {
                val temp = get(i)
                var j = i
                while (j >= interval && get(j - interval) > temp) {
                    set(j, get(j - interval))
                    j -= interval
                }
                set(j, temp)
            }
            interval /= ratio
        }
    }

    /**
     * Bucket sort is a stable, distribution sorting algorithm.
     *
     *
     * 1. Create an array of buckets, each bucket holds elements that fall in some continuous, disjoint intervals.
     * 2. Put elements into their corresponding buckets.
     * 3. Sort each bucket using some sorting algorithm.
     * 4. Flatten the sorted bucket array(concat)
     *
     *
     * - select a interval so that elements are uniformly distributed to buckets
     * Average time: O(n + n ^ 2 / k + k)
     * Best time: O(n ^ 2)
     * Worsrt time: O(n * k)
     *
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     */
    private fun bucketSort(start: Int, end: Int) {
        val n = sqrt(length.toDouble()).toInt()
        if (length <= 1 || n <= 0) {
            return
        }
        val max = get(max(start, end)).hashCode().toDouble()
        val min = get(min(start, end)).hashCode().toDouble()
        // interval range of elements in each bucket
        val range = ceil((max - min) / n).toInt()
        // all elements are equal
        if (range == 0) {
            return
        }
        val buckets: Array<ArrayList<T>> = Array(n) { ArrayList() }
        for (i in 0 until n) {
            buckets[i] = ArrayList(range.coerceAtMost(end - start))
        }
        // put elements into buckets
        for (i in start until end) {
            val bucketId = ((get(i).hashCode() - min) / range).toInt()
            val bucket = buckets[if (bucketId == 0) 0 else bucketId - 1]
            bucket.add(get(i))
        }
        // flatten the array of buckets
        var k = start
        for (i in 0 until n) {
            val bucket = buckets[i]
            bucket.sort()
            for (t in bucket) {
                set(k, t)
                k++
            }
        }
    }

    /**
     * description Counting sort is a stable, distribution sorting algorithm.
     *
     *
     * 1. Determine the range of values, initialize an array to count the frequency of each elements
     * 2. Traverse the array, add 1 to the counter of the element in the counting array
     * 3. Calculate the cumulative counts, this helps to build the sorted array
     * 4. Build the sorted array
     *
     *
     * - uses extra space to achieve start time complexity
     * - element values are used as index in counting
     * - cumulative counts transform the frequency information to position information (costs k time)
     * Worst time: O(n + k)
     *
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     */
    @Suppress("UNCHECKED_CAST")
    private fun countingSort(start: Int, end: Int) {
        val max = get(max(start, end)).hashCode()
        val min = get(min(start, end)).hashCode()
        val counts = IntArray(max - min + 1)
        for (i in start until end) {
            val elem = get(i)
            counts[elem.hashCode() - min]++
        }
        for (i in 1 until counts.size) {
            counts[i] += counts[i - 1]
        }
        val temp = arrayOfNulls<Comparable<T>>(end - start) as Array<T>
        for (i in start until end) {
            val elem = get(i)
            val pos = counts[elem.hashCode() - min]
            temp[pos - 1] = elem
            counts[elem.hashCode()]--
        }
        temp.copyInto(data, start)
    }

    /**
     * Heap sort is an unstable, in-place comparison sorting algorithm
     *
     *
     * 1.[O(n)] Build a min heap out of the array
     * 2.[O(n log n)] adjust the heap for last len - i elements
     * 3. repeat 2. until no elements left to be heapified
     *
     *
     * - each iteration puts one element into its correct position, so the last i elements are sorted
     * Best time: O(n log n)
     * WorstRime: O(n log n)
     *
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     */
    private fun heapSort(start: Int, end: Int) {
        fun heapify(length: Int, root: Int) {
            var parent = root
            var child = 2 * root - start + 1
            while (child < length) {
                if (child + 1 < length && get(child) < get(child + 1)) {
                    child++
                }
                if (get(parent) < get(child)) {
                    swap(parent, child)
                    parent = child
                    child = 2 * child - start + 1
                } else {
                    break
                }
            }
        }
        for (i in (end + start) / 2 - 1 downTo 0) {
            heapify(end, i)
        }
        for (i in end - 1 downTo start) {
            // swap the max and the last node
            swap(i, start)
            // restore heap property
            heapify(i, start)
        }
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     * Radix Sort is a stable, distribution sorting algorithm
     *
     *
     * 1. For each digits, perform a counting sort
     *
     *
     * - value at digit n of integer a is calculated by $$\frac{a}{10^n} \mod 10$$
     * @worstTime O(w * n)
     */
    @Suppress("UNCHECKED_CAST")
    private fun radixSort(start: Int, end: Int) {
        val max = get(max()).hashCode()
        val digits = max.toString().length
        for (j in 0 until digits) {
            val exp = (10.0).pow(j).toInt()
            val counts = IntArray(10)
            // counting sort
            for (i in start until end) {
                val jthDigit = get(i).hashCode() / exp % 10
                counts[jthDigit]++
            }
            for (i in 1..9) {
                counts[i] += counts[i - 1]
            }
            val temp = arrayOfNulls<Comparable<T>>(end - start) as Array<T>
            for (i in end - 1 downTo start) {
                val ithDigit = get(i).hashCode() / exp % 10
                temp[counts[ithDigit] - 1] = get(i)
                counts[ithDigit]--
            }
            temp.copyInto(data, start)
        }
    }

    /**
     * @param start
     * @param end
     * @description Selection sort is a stable, in-place comparison sorting algorithm.
     *
     *
     * 1. Assume the current element is the min element
     * 2. Find the minimum element in the rest of the unsorted array
     * 3. Swap the current element with the minimum one
     * 4. Repeat 1., 2., 3. for n times
     *
     *
     * - each iteration puts one element into its correct position, so the first i elements are sorted
     * @bestTime O(n ^ 2)
     * @avgTime O(n ^ 2)
     * @worstTime O(n ^ 2)
     */
    private fun selectionSort(start: Int, end: Int) {
        for (i in start until end) {
            var minPos = i
            var minVal = get(i)
            for (j in i + 1 until end) {
                if (get(j) < minVal) {
                    minPos = j
                    minVal = get(j)
                }
            }
            swap(i, minPos)
        }
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     * @description Insertion sort is a stable, in-place comparison soring algorithm.
     *
     *
     * 1. Get the next element
     * 2. Find the place to insert it by comparing to and moving the previous sorted elements one by one
     * 3. Insert the element and repeat 1., 2. for n times.
     *
     *
     * - each iteration puts one element into its correct position, so the first i elements are sorted
     */
    private fun insertionSort(start: Int, end: Int) {
        for (i in start until end) {
            val current = get(i)
            var j = i - 1
            // if the element is smaller than the previous one(s)
            while (j >= start && current < get(j)) {
                // move to reserve a spare position for the coming element
                set(j + 1, get(j))
                j--
            }
            set(j + 1, current)
        }
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     * @description Quick sort is an unstable, in-place comparison sorting algorithm.
     *
     *
     * 1. Initialize left and right pointers, and calculate the pivot
     * 2. Increment left pointer and decrement right pointer to find any inverted pair, if any, swap them and continue
     * 3. When two pointers surpass each other, bipartite the array there into [start, right + 1) and [right, end)
     * 4. Quick sort the subarray(s) with size larger than 1
     *
     *
     * - terminates when subarray has length 1 or 0
     * - partition finishes when left pointer and right pointer cross
     * - partition 1. after pointers meet at pivot: [<pivot (pr), pivot,(pl)>pivot] or 2. [<=pivot (pr), (pl) >=pivot]
     * - if any element equals to the pivot, stop moving pointers and swap
     * - pointers must be updated after a swap, or the loop may never end
     * @bestTime O(n log n)
     * @avgTime O(n log n)
     * @worstTime O(n ^ 2)
    </pivot> */
    private fun quickSort(start: Int, end: Int) {
        // termination condition
        if (end - start < 2) {
            return
        }
        // initialize left and right pointers
        var leftPointer = start
        var rightPointer = end - 1
        // select a random pivot
        val pivot = get(java.util.Random().nextInt(end - start) + start)
        // find the partition point
        while (leftPointer <= rightPointer) {
            // find any element from left that is larger than the pivot
            while (get(leftPointer) < pivot) {
                leftPointer++
            }
            // find any element from right that is smaller than the pivot
            while (get(rightPointer) > pivot) {
                rightPointer--
            }
            // swap the inverted pair and increment the pointers to continue
            if (leftPointer <= rightPointer) {
                swap(leftPointer, rightPointer)
                leftPointer++
                rightPointer--
            }
        }
        quickSort(start, leftPointer)
        quickSort(leftPointer, end)
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     * @description Merge sort is a stable, comparison based sorting algorithm.
     *
     *
     * 1. equally bipartite the array into two subarrays
     * 2. repeat 1. until the array is fully divided into singletons
     * 3. copy the sorted subarrays to a left array and a right array
     * 4. merge two sorted arrays
     * 5. repeat 3., 4. on other sorted subarrays
     *
     *
     * - termination condition: a singleton array or an empty array
     * - after comparison, there must be one or more elements left in either left or right subarray
     * @bestTime O(n log n)
     * @avgTime O(n log n)
     * @worstTime O(n log n)
     * @worstSpace O(n)
     */
    @Suppress("UNCHECKED_CAST")
    private fun mergeSort(start: Int, end: Int) {
        // if there's only one element in the array, it's sorted
        if (end - start <= 1) {
            return
        }
        val mid = (start + end) / 2
        // sort left subarray
        mergeSort(start, mid)
        // sort right subarray
        mergeSort(mid, end)

        // merge
        val leftLength = mid - start
        val rightLength = end - mid
        // create temp array for comparison and merge
        val leftArray = Array<Comparable<T>>(leftLength) { i -> get(start + i) } as Array<T>
        val rightArray = Array<Comparable<T>>(rightLength) { i -> get(mid + i) } as Array<T>
        // initialize three pointers
        var leftId = 0
        var rightId = 0
        var arrayId = start
        // merge left and right array
        while (leftId < leftLength && rightId < rightLength) {
            // since left and right are sorted, select left element if it's smaller, or select right element
            if (leftArray[leftId] < rightArray[rightId]) {
                set(arrayId, leftArray[leftId])
                leftId++
            } else {
                set(arrayId, rightArray[rightId])
                rightId++
            }
            arrayId++
        }
        // add the remaining elements in either subarray
        while (leftId < leftLength) {
            set(arrayId, leftArray[leftId])
            leftId++
            arrayId++
        }
        while (rightId < rightLength) {
            set(arrayId, rightArray[rightId])
            rightId++
            arrayId++
        }
    }

    /**
     * @param start start bound (inclusive)
     * @param end end bound (exclusive)
     * @description Bubble sort is a stable, in-place comparison sorting algorithm
     *
     *
     * 1. compare the current element to the next one, if they are inverted, swap them
     * 2. repeat 1. for n times
     *
     *
     * - each iteration puts one element into its correct position, so the last i elements are sorted
     * - if in some iteration no element is swapped, then the array is sorted
     * @bestTime O(n)
     * @avgTime O(n ^ 2)
     * @worstTime O(n ^ 2)
     */
    private fun bubbleSort(start: Int, end: Int) {
        var sorted = true
        var i = start
        while (i < end || !sorted) {
            // assume sorted at the beginning of each iteration
            sorted = true
            for (j in 0 until end - i - 1) {
                // compare with the next element
                if (get(j) > get(j + 1)) {
                    swap(j, j + 1)
                    sorted = false
                }
            }
            i++
        }
    }

    private fun sleepSort(start: Int, end: Int) {
        val temp = ArrayList<T>(data.size)
        suspend fun sleep(element: T): T {
            delay(element.hashCode().toLong())
            return element
        }
        runBlocking {
            for (i in start until end) {
                val elem = async { sleep(get(i)) }
                temp.add(elem.await())
            }
        }
        for (i in start until end) {
            data[i] = temp[i]
        }
    }

    private fun timSort(start: Int, end: Int) {

    }

    override fun length(): Int {
        return length
    }

    override fun head(): T {
        return get(lowerBound)
    }

    override fun last(): T {
        return get(length - 1)
    }

    override fun toString(): String {
        val iMax = upperBound - 1
        if (iMax == -1) {
            return "[]"
        }
        val b = StringBuilder()
        b.append('[')
        var i = lowerBound
        while (true) {
            b.append(data[i])
            if (i == iMax) {
                return b.append(']').toString()
            }
            b.append(", ")
            i++
        }
    }
}