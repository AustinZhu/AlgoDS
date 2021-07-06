package dev.austinzhu.pattern.adt

trait Bool extends Sum[Unit, Unit] {}

object Bool {

  val TRUE: Bool = Bool.True()
  val FALSE: Bool = Bool.False()

  final case class True private() extends Bool

  final case class False private() extends Bool
}