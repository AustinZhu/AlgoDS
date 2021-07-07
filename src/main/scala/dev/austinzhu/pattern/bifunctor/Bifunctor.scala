package dev.austinzhu.pattern.bifunctor

trait Bifunctor[F[_, _]] {

  def bimap[A, B, A1, B1](f: A => A1, g: B => B1, fab: F[A, B]): F[A1, B1]

  def first[A, B, A1](f: A => A1, fab: F[A, B]): F[A1, B]

  def second[A, B, B1](g: B => B1, fab: F[A, B]): F[A, B1]
}