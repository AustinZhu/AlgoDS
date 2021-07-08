package dev.austinzhu.pattern.order

import dev.austinzhu.pattern.adt.Bool

trait Preord[A] {

  def <(a: A, b: A): Bool
}