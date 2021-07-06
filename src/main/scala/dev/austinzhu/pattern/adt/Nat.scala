package dev.austinzhu.pattern.adt

trait Nat extends Sum[Unit, Nat] {}

object Nat {

  val ZERO: Nat = Nat.Zero()

  final case class Zero private() extends Nat {}

  final case class Succ(n: Nat) extends Nat {}
}