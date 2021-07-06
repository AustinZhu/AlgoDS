package dev.austinzhu.pattern.adt

object Bool {
  val TRUE = new Bool.True
  val FALSE = new Bool.False

  class True private() extends Bool {
  }

  class False private() extends Bool {
  }
}

trait Bool extends Sum[Unit, Unit] {}