package dev.austinzhu.pattern.functor

trait Functor[F[A]] {

  def fmap[A, B](fn: Function[A, B], f: F[A]): F[B]
}