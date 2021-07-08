package dev.austinzhu.algo.structure.array

import dev.austinzhu.algo.interfaces.*
import dev.austinzhu.algo.structure.Sequence
import java.security.NoSuchAlgorithmException
import kotlin.Array
import kotlin.random.Random

open class Vector<T : Comparable<T>>(val capacity: Int) : Operatable<T>, Searchable<T>, Sortable, Sequence<T> {

    @Suppress("UNCHECKED_CAST")
    val data = arrayOfNulls<Any?>(capacity) as Array<T?>

    var lowerBound = 0
        set(lower) {
            field = lower
            if (lower < 0) throw IllegalArgumentException("Cannot set lower bound below 0")
            length = upperBound - lower
        }

    var upperBound = 0
        set(upper) {
            field = upper
            if (upper > data.size) throw IllegalArgumentException("Cannot set upper bound beyond capacity")
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
            normalize()
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
        when (sa) {
            SearchingAlgorithm.LINEAR -> return linearSearch(element, start, end)
            SearchingAlgorithm.BINARY -> return binarySearch(element, start, end)
            SearchingAlgorithm.JUMP -> return jumpSearch(element, start, end)
            else -> throw NoSuchAlgorithmException(sa.name.lowercase() + " search is not supported")
        }
    }

    private fun linearSearch(element: T, start: Int, end: Int): Int {
        return -1
    }

    private fun binarySearch(element: T, start: Int, end: Int): Int {
        return -1
    }

    private fun jumpSearch(element: T, start: Int, end: Int): Int {
        return -1
    }

    override fun exist(element: T): Boolean {
        return search(element) >= 0
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
        TODO("Not yet implemented")
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

    private fun normalize() {
        data.copyInto(data, 0, lowerBound, upperBound)
        lowerBound = 0
        upperBound = length
    }
}