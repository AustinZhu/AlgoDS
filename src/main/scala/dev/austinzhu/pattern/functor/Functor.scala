package dev.austinzhu.pattern.functor

trait Functor[F[_]] {

  def fmap[A, B](fn: A => B, f: F[A]): F[B]
}