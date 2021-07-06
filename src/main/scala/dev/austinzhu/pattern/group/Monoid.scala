package dev.austinzhu.pattern.group

type.Type

trait Monoid[A <: SemiGroup[A] with Type] {
  def unit
}