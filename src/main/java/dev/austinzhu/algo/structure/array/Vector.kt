package dev.austinzhu.algo.structure.array

import dev.austinzhu.algo.interfaces.*
import dev.austinzhu.algo.structure.Sequence
import kotlin.Array
import kotlin.random.Random

open class Vector<T : Comparable<T>>(capacity: Int) : Operatable<T>, Searchable<T>, Sortable, Sequence<T> {

    @Suppress("UNCHECKED_CAST")
    val data = arrayOfNulls<Any?>(capacity) as Array<T>

    var lowerBound = 0

    var upperBound = 0
        set(upperBound) {
            field = upperBound
            this.length = upperBound - lowerBound
        }

    var length = 0

    companion object {
        fun init(size: Int, bound: Int, random: Random): Vector<Int> {
            if (size < 0) {
                throw IllegalArgumentException("Size must be a natural number")
            }
            val capacity = random.nextInt(size + 1)
            val intVector = Vector<Int>(capacity)
            for (i in 0..capacity) {
                intVector.append(random.nextInt(bound))
            }
            return intVector
        }

        fun <T : Comparable<T>> merge(a: Vector<T>, b: Vector<T>): Vector<T> {
            val newLength = a.length + b.length
            val newVector = Vector<T>(newLength)
            if (a.length >= 0) {
                a.data.copyInto(newVector.data)
                b.data.copyInto(newVector.data, a.length)
            }
            newVector.upperBound = newLength
            return newVector
        }
    }

    override fun fill(vararg elements: T) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun set(idx: Int, `object`: T) {
        TODO("Not yet implemented")
    }

    override fun get(idx: Int): T {
        TODO("Not yet implemented")
    }

    override fun insert(idx: Int, `object`: T) {
        TODO("Not yet implemented")
    }

    override fun delete(idx: Int): T {
        TODO("Not yet implemented")
    }

    override fun append(element: T) {
        TODO("Not yet implemented")
    }

    override fun eject(): T {
        TODO("Not yet implemented")
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

    override fun sort(sa: SortingAlgorithm?) {
        TODO("Not yet implemented")
    }

    override fun sort(start: Int, end: Int, sa: SortingAlgorithm?) {
        TODO("Not yet implemented")
    }

    override fun length(): Int {
        TODO("Not yet implemented")
    }

    override fun head(): T {
        TODO("Not yet implemented")
    }

    override fun last(): T {
        TODO("Not yet implemented")
    }
}