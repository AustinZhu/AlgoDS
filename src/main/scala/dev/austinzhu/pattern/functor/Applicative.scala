package dev.austinzhu.pattern.functor

trait Applicative[F[_]] extends Functor[F] {

  def pure[A](a: A): F[A]

  def sapply[A, B](f: F[A => B], fa: F[A]): F[B]
}