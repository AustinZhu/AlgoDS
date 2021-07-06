package dev.austinzhu.pattern.adt

object List {
  val NIL = new List.Nil[AnyRef]

  def Cons[A](x: A, xs: List[A]) = new List.Cons[A](x, xs)

  final class Nil[A] private() extends List[A] {
  }

  final class Cons[A] private(val x: A, val xs: List[A]) extends Pair[A, List[A]](x, xs) with List[A] {
  }
}

trait List[A] extends Sum[Unit, Pair[A, List[A]]] {}