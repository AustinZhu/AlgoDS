package dev.austinzhu.pattern.group

trait Monoid[A] extends SemiGroup[A] {

  def unit: A
}