package dev.austinzhu.pattern.order

type.adt.Bool

type.Type

trait Preorder[A <: Type] {
  def le(a: A, b: A)
}