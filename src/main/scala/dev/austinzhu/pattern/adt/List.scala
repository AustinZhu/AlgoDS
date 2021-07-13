package dev.austinzhu.pattern.adt

object List {

  val NIL: List[_] = List.Nil()

  final case class Nil[A] private() extends List[A]

  final case class Cons[A] private(x: A, xs: List[A]) extends List[A]
}

trait List[A] extends Sum[Unit, Pair[A, List[A]]]