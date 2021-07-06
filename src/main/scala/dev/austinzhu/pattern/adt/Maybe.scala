package dev.austinzhu.pattern.adt

import jdk.jshell.spi.ExecutionControl.NotImplementedException

object Maybe {
  val NOTHING = new Maybe.Nothing[AnyRef]

  def Just[A](a: A) = new Maybe.Just[A](a)

  final class Nothing[A] private() extends Maybe[A] {
  }

  final class Just[A] private(val x: A) extends Id[A](x) with Maybe[A] {
  }
}

trait Maybe[A] extends Sum[Unit, A] with Nothing {
  def fromJust(x: Maybe.Just[A]) = x.v

  @throws[NotImplementedException]
  def fromMaybe(a: A, x: Maybe[A]): A = {
    if (x.isInstanceOf[Maybe.Nothing[A]]) return a
    if (x.isInstanceOf[Maybe.Just[A]]) return y.v
    throw new ExecutionControl.NotImplementedException("Non-exaustive patterns")
  }

  @throws[NotImplementedException]
  def isJust(x: Maybe[A]): Bool = {
    if (x.isInstanceOf[Maybe.Nothing[A]]) return Bool.FALSE
    if (x.isInstanceOf[Maybe.Just[A]]) return Bool.TRUE
    throw new ExecutionControl.NotImplementedException("Non-exaustive patterns")
  }

  @throws[NotImplementedException]
  def isNothing(x: Maybe[A]): Bool = {
    if (x.isInstanceOf[Maybe.Nothing[A]]) return Bool.TRUE
    if (x.isInstanceOf[Maybe.Just[A]]) return Bool.FALSE
    throw new ExecutionControl.NotImplementedException("Non-exaustive patterns")
  }

  def fmap[B](fn: Nothing, f: Maybe[_]): Maybe[B] = {
    if (f.isInstanceOf[Maybe.Nothing[_]]) return Maybe.NOTHING.asInstanceOf[Maybe[B]]
    null
  }
}