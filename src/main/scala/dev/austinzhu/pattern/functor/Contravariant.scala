package dev.austinzhu.pattern.functor

type.Function

type.Type

trait Contravariant[FA <: Contravariant[FA, A], A <: Type] {
  def contramap[B <: Type, FB <: Contravariant[FB, B]](f: Function[B, A], fa: Contravariant[FA, A])
}