package dev.austinzhu.pattern.adt

class Pair[A, B] private[adt](val a: A, val b: B) extends Product[A, B]