package dev.austinzhu.pattern.functor

type.Function

type.Type

trait Monad[M <: Type with Functor[M, A] with Applicative[M, A] with Monad[M, A], A <: Type] {
  def ret(a: A)

  def compose[B <: Type, MB <: Monad[MB, B] with Type with Functor[MB, B] with Applicative[MB, B], MF <: Monad[MF, Function[A, MB]] with Type with Functor[MF, Function[A, MB]] with Applicative[MF, Function[A, MB]]](m: Monad[M, A], f: Monad[MF, Function[A, MB]])
}