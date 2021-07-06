package dev.austinzhu.pattern.functor

type.Type

trait Compose[F <: Functor[F, G] with Type, G <: Functor[G, A] with Type, A <: Type] {
  def compose
}