package dev.austinzhu.pattern.functor

type.Type

trait Identity[A <: Type] extends Functor[Identity[A], A] with Type {
  def id
}