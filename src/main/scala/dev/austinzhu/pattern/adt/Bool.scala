package dev.austinzhu.pattern.adt

import dev.austinzhu.pattern.adt.Bool.{FALSE, TRUE}

object Bool {

  val TRUE: Bool = Bool.True()
  val FALSE: Bool = Bool.False()

  final case class True private() extends Bool

  final case class False private() extends Bool
}

sealed trait Bool extends Sum[Unit, Unit] {

  val otherwise: Bool = TRUE

  def and(a: Bool, b: Bool): Bool = a match {
    case Bool.True() => b
    case Bool.False() => a
  }

  def or(a: Bool, b: Bool): Bool = a match {
    case Bool.True() => a
    case Bool.False() => b
  }

  def not(a: Bool): Bool = a match {
    case Bool.True() => FALSE
    case Bool.False() => TRUE
  }

  def bool(x: _, y: _, b: Bool): _ = b match {
    case Bool.True() => y
    case Bool.False() => x
  }
}