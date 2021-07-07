package dev.austinzhu.pattern

trait Function[A <: Type, B <: Type] extends Type {
}

object Function {

  def id[A <: Type](x: A): _ = x

  def compose[A <: Type, B <: Type, C <: Type](f: Function[B, C], g: Function[A, B]): Function[A, C] = {
    (a: A) => apply(f, apply(g, a))
  }

  def apply[A <: Type, B <: Type](f: Function[A, B], a: A): B

  def fmap[C <: Type](f: Function[_ >: B, _ <: C], g: Function[A, _]) = (a: A) => f.apply(g.apply(a))

  def pure(b: B) = (x: A) => b
}