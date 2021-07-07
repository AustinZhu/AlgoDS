package dev.austinzhu.pattern.adt

sealed trait Nat extends Sum[Unit, Nat]

object Nat {

  val ZERO: Nat = Nat.Zero()

  final case class Zero private() extends Unit with Nat

  final case class Succ(n: Nat) extends Id[Nat] with Nat
}