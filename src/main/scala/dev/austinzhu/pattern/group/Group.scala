package dev.austinzhu.pattern.group

trait Group[A] extends Monoid[A] {

  def invert(a: A): A
}