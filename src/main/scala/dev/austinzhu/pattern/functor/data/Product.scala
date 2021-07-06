package dev.austinzhu.pattern.functor.data

type.Type

class Product[F <: Functor[F, A] with Type, G <: Functor[G, A] with Type, A <: Type] private[data](var f: F, var g: G) {
}