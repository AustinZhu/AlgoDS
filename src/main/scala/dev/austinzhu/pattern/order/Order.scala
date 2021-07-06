package dev.austinzhu.pattern.order

type.adt.Bool

type.Type

trait Order[A <: Preorder[A] with Equality[A] with Type] {
  def leq(a: A, b: A)
}