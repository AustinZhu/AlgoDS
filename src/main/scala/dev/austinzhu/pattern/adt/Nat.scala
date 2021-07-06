package dev.austinzhu.pattern.adt

object Nat {
  val ZERO = new Nat.Zero

  def Succ(n: Nat) = new Nat.Succ(n)

  final class Zero private() extends Nat {
  }

  final class Succ private(val n: Nat) extends Id[Nat](n) with Nat {
  }
}

trait Nat extends Sum[Unit, Nat] {}