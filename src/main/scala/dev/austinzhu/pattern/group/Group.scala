package dev.austinzhu.pattern.group

type.Type

trait Group[A <: Monoid[A] with Type with SemiGroup[A]] {
  def invert(a: A)
}