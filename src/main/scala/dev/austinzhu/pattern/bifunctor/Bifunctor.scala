package dev.austinzhu.pattern.bifunctor

trait Bifunctor[F <: Bifunctor[F, A, B], A, B] {
  def bimap[G <: Bifunctor[G, A1, B1], A1, B1](f: Nothing, g: Nothing, fab: Bifunctor[F, A, B])

  def first[G <: Bifunctor[G, A1, B], A1](f: Nothing, fab: Bifunctor[F, A, B])

  def second[G <: Bifunctor[G, A, B1], B1](g: Nothing, fab: Bifunctor[F, A, B])
}