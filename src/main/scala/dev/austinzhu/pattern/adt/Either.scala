package dev.austinzhu.pattern.adt

sealed trait Either[A, B] extends Sum[A, B] {}

object Either {

  final case class Left[A, B](val a: A) extends Either[A, Nothing]

  final case class Right[A, B](val b: B) extends Either[Nothing, B]
}