package dev.austinzhu.pattern.order

import dev.austinzhu.pattern.adt.Bool

trait PreOrd[A] {

  def <(a: A, b: A): Bool
}