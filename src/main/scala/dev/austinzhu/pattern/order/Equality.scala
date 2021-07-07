package dev.austinzhu.pattern.order

import dev.austinzhu.pattern.adt.Bool

trait Equality[A] {

  def ==(a: A, b: A): Bool
}