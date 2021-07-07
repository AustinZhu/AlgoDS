package dev.austinzhu.pattern.order

import dev.austinzhu.pattern.adt.Bool

trait Order[A <: Preorder[A] with Equality[A]] {

  def <=(a: A, b: A): Bool
}