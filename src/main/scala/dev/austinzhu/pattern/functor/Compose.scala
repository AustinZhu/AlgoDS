package dev.austinzhu.pattern.functor

class Compose[F[_] <: Functor[_], G[_] <: Functor[_], A](compose: F[G[A]])