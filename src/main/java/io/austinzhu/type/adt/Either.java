package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Either<A extends Type<A>, B extends Type<B>> implements Sum<A, B>, Type<Either<A, B>> {

    class Left implements Sum<A, B> {
        Type<Unit> a;

        Left(Type<Unit> a) {
            this.a = a;
        }
    }

    class Right implements Sum<A, B> {
        Type<A> b;

        Right(Type<A> b) {
            this.b = b;
        }
    }
}
