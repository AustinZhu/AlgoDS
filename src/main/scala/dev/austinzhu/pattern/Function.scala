package dev.austinzhu.pattern

type.functor.Applicative

type.functor.Functor

import java.util.Objects

@FunctionalInterface trait Function[A <: Type, B <: Type] extends Type with Nothing with Nothing {
  def id = (x: A) => x

  def compose[C <: Type](f: Function[C, A]) = {
    Objects.requireNonNull(f)
    (c: C) => apply(f.apply(c))
  }

  def apply(a: A)

  def fmap[C <: Type](f: Function[_ >: B, _ <: C], g: Function[A, _]) = (a: A) => f.apply(g.apply(a))

  def pure(b: B) = (x: A) => b
}