package dev.austinzhu.pattern.bifunctor

trait Const[A, _] extends Bifunctor[Const] {

  def const: A
}
