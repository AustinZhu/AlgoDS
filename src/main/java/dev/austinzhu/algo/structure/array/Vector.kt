package dev.austinzhu.algo.structure.array

import dev.austinzhu.algo.interfaces.*
import dev.austinzhu.algo.structure.Sequence
import kotlin.Array
import kotlin.random.Random

open class Vector<T : Comparable<T>>(capacity: Int) : Operatable<T>, Searchable<T>, Sortable, Sequence<T> {

    @Suppress("UNCHECKED_CAST")
    val data = arrayOfNulls<Any?>(capacity) as Array<T?>

    var lowerBound = 0
        set(lower) {
            field = lower
            length = upperBound - lower
        }

    var upperBound = 0
        set(upper) {
            field = upper
            length = upper - lowerBound
        }

    var length = 0

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
    }

    override fun fill(vararg elements: T) {
        for (e in elements) append(e)
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
        TODO("Not yet implemented")
    }

    override fun delete(idx: Int): T {
        if (idx in lowerBound until upperBound) {
            val del = get(idx)
            set(idx, null)
            return del
        }
        throw IndexOutOfBoundsException(idx)
    }

    override fun append(element: T) {
        if (upperBound >= data.size) throw IndexOutOfBoundsException("Vector is full")
        upperBound++
        set(length - 1, element)
    }

    override fun eject(): T {
        if (length <= 0) throw IndexOutOfBoundsException("Nothing to eject")
        val del = get(length - 1)
        set(length - 1, null)
        upperBound = length - 1
        return del
    }

    override fun prepend(element: T) {
        TODO("Not yet implemented")
    }

    override fun pop(): T {
        TODO("Not yet implemented")
    }

    override fun search(element: T): Int {
        TODO("Not yet implemented")
    }

    override fun search(element: T, start: Int, end: Int): Int {
        TODO("Not yet implemented")
    }

    override fun search(element: T, sa: SearchingAlgorithm?): Int {
        TODO("Not yet implemented")
    }

    override fun search(element: T, start: Int, end: Int, sa: SearchingAlgorithm?): Int {
        TODO("Not yet implemented")
    }

    override fun exist(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun sort() {
        TODO("Not yet implemented")
    }

    override fun sort(start: Int, end: Int) {
        TODO("Not yet implemented")
    }

    override fun sort(sa: SortingAlgorithm) {
        TODO("Not yet implemented")
    }

    override fun sort(start: Int, end: Int, sa: SortingAlgorithm) {
        TODO("Not yet implemented")
    }

    override fun length(): Int {
        return length
    }

    override fun head(): T {
        return data[lowerBound]!!
    }

    override fun last(): T {
        return data[upperBound - 1]!!
    }
}