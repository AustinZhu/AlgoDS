package dev.austinzhu.pattern.adt

object Maybe {

  val NOTHING: Maybe[_] = Maybe.Nothing()

  final case class Nothing private() extends Unit with Maybe[_]

  final case class Just[A] private(x: A) extends Id[A](x) with Maybe[A]
}

trait Maybe[A] extends Sum[Unit, A] {

  def fromJust(x: Maybe[A]): A = x match {
    case Maybe.Just(x) => x
  }

  def fromMaybe(a: A, x: Maybe[A]): A = x match {
    case Maybe.Just(x) => x
    case Maybe.Nothing() => a
  }

  def isJust(x: Maybe[A]): Bool = x match {
    case _: Maybe.Just[A] => Bool.TRUE
    case _: Maybe.Nothing => Bool.FALSE
  }

  def isNothing(x: Maybe[A]): Bool = x match {
    case _: Maybe.Just[A] => Bool.FALSE
    case _: Maybe.Nothing => Bool.TRUE
  }

  def fmap[B](fn: A => B, f: Maybe[A]): Maybe[B] = f match {
    case Maybe.Just(x) => Maybe.Just(fn(x))
    case Maybe.Nothing() => Maybe.Nothing()
  }
}