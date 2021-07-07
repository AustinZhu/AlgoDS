package dev.austinzhu.pattern.adt

import dev.austinzhu.pattern.Type

object List {

  val NIL: List[_] = List.Nil()

  final case class Nil[A] private() extends Unit with List[A]

  final case class Cons[A] private(x: A, xs: List[A]) extends Pair[A, List[A]](x, xs) with List[A]
}

trait List[A] extends Sum[Unit, Pair[A, List[A]]] with Type