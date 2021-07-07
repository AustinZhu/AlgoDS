package dev.austinzhu.pattern.adt

import dev.austinzhu.pattern.Type

sealed trait Either[A, B] extends Sum[A, B] with Type

object Either {

  final case class Left[A, B](a: A) extends Either[A, B]

  final case class Right[A, B](b: B) extends Either[A, B]
}