package dev.austinzhu.pattern.group

type.Type

trait SemiGroup[A <: Type] {
  def assoc(a: A, b: A)
}