package dev.austinzhu.pattern.adt

object Maybe {

  val NOTHING: Maybe[_] = Maybe.Nothing()

  final case class Nothing[A] private() extends Maybe[A]

  final case class Just[A] private(x: A) extends Maybe[A]
}

trait Maybe[A] extends Sum[Unit, A] {

  def fromJust(x: Maybe[A]): A = x match {
    case Maybe.Just(x) => x
  }

  def fromMaybe(a: A, x: Maybe[A]): A = x match {
    case Maybe.Just(x) => x
    case Maybe.Nothing() => a
  }

  def isJust(x: Maybe[_]): Bool = x match {
    case _: Maybe.Just[_] => Bool.TRUE
    case _: Maybe.Nothing[_] => Bool.FALSE
  }

  def isNothing(x: Maybe[_]): Bool = x match {
    case _: Maybe.Just[_] => Bool.FALSE
    case _: Maybe.Nothing[_] => Bool.TRUE
  }

  def fmap[B](fn: A => B, f: Maybe[A]): Maybe[B] = f match {
    case Maybe.Just(x) => Maybe.Just(fn(x))
    case Maybe.Nothing() => Maybe.Nothing[B]()
  }
}