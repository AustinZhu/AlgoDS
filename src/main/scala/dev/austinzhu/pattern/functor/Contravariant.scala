package dev.austinzhu.pattern.functor

trait Contravariant[F[_]] {

  def contraMap[A, B](f: B => A, fa: F[A]): F[B]
}