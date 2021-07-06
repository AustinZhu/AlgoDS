package dev.austinzhu.pattern.adt

import dev.austinzhu.pattern.Type

sealed trait Bool extends Sum[Unit, Unit] with Type {}

object Bool {

  val TRUE: Bool = Bool.True()
  val FALSE: Bool = Bool.False()

  final case class True private() extends Bool

  final case class False private() extends Bool
}