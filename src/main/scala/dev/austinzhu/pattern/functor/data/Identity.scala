package dev.austinzhu.pattern.functor.data

import dev.austinzhu.pattern.functor.Functor

class Identity[T](val id: T) extends Functor[Identity] {

  override def fmap[A, B](fn: A => B, fid: Identity[A]): Identity[B] = new Identity(fn(fid.id))
}