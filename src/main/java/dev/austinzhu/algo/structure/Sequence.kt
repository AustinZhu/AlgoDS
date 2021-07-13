package dev.austinzhu.algo.structure;

interface Sequence<T> {

    fun length(): Int

    fun head(): T

    fun last(): T

    fun reverse()

    fun reverse(start: Int, end: Int)

    fun slice(start: Int, end: Int)

    fun toArray(): Array<T?>

    fun toArray(start: Int, end: Int): Array<T?>
}
