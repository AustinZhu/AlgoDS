package dev.austinzhu.pattern.group

trait SemiGroup[A] {

  def assoc(a: A, b: A): A
}