package dev.austinzhu.pattern.functor

type.Function

type.Type

@FunctionalInterface trait Functor[F <: Type with Functor[F, _$1], A <: Type] {
  def fmap[B <: Type](fn: Function[_ >: A, _ <: B], f: F)
}