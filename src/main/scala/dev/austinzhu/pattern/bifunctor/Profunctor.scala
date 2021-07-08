package dev.austinzhu.pattern.bifunctor

trait Profunctor[F[_, _]] {

  def dimap[A1, A, B, B1](f: A1 => A, g: B => B1, fab: F[A, B]): F[A1, B1]

  def lmap[A1, A, B](f: A1 => A, fab: F[A, B]): F[A1, B]

  def rmap[A, B, B1](g: B => B1, fab: F[A, B]): F[A, B1]
}