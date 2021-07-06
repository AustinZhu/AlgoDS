package dev.austinzhu.pattern.functor

type.Function

type.Type

trait Applicative[F <: Type with Applicative[F, _$1], A <: Type] extends Functor[F, A] {
  def pure(a: A)

  def sapply[B <: Type with Functor[B, Function[A, B]]](f: Applicative[B, Function[A, B]], fa: F)
}