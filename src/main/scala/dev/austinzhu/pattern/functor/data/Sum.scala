package dev.austinzhu.pattern.functor.data

type.Type

object Sum {
  class InL[F <: Functor[F, A] with Type, G <: Functor[G, A] with Type, A <: Type] private[data](var f: F) extends Sum[F, G, A] {
  }

  class InR[F <: Functor[F, A] with Type, G <: Functor[G, A] with Type, A <: Type] private[data](var g: G) extends Sum[F, G, A] {
  }
}