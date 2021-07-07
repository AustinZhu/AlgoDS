package dev.austinzhu.pattern.functor

import dev.austinzhu.pattern.{Function, Type}

trait Functor[F[A <: Type] <: Type] {

  def fmap[A <: Type, B <: Type](fn: Function[A, B], f: F[A]): F[B]
}