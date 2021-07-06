package dev.austinzhu.pattern.order

type.adt.Bool

type.Type

trait Equality[A <: Type] {
  def equal(a: A, b: A)
}