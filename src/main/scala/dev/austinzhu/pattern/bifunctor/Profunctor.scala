package dev.austinzhu.pattern.bifunctor

trait Profunctor[A, B] {
  def dimap[A1, B1](f: Nothing, g: Nothing, F: Profunctor[A, B])

  def lmap[A1](f: Nothing, F: Profunctor[A, B])

  def rmap[B1](g: Nothing, F: Profunctor[A, B])
}