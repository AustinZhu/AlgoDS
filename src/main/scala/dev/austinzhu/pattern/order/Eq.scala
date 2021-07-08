package dev.austinzhu.pattern.order

import dev.austinzhu.pattern.adt.Bool

trait Eq[A] {

  def ==(a: A, b: A): Bool
}