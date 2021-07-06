package dev.austinzhu.pattern.functor

type.Type

type.bifunctor.Bifunctor

trait Const[A <: Type, B <: Type] extends Nothing with Type {
  def constant
}