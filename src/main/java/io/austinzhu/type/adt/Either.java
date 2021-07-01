package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public sealed interface Either<A extends Type<A>, B extends Type<B>>
        extends Sum<A, B>, Type<Either<A, B>>
        permits Either.Left, Either.Right {

    final class Left<A extends Type<A>, B extends Type<B>> extends Sum.A<A, B> implements Either<A, B> {

        public static <A extends Type<A>, B extends Type<B>> Either<A, B> left(A a) {
            return new Left<>(a);
        }

        private Left(A a) {
            super(a);
        }
    }

    final class Right<A extends Type<A>, B extends Type<B>> extends Sum.B<A, B> implements Either<A, B> {

        public static <A extends Type<A>, B extends Type<B>> Either<A, B> right(B b) {
            return new Right<>(b);
        }

        private Right(B b) {
            super(b);
        }
    }
}
