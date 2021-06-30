package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public interface Sum<A extends Type<A>, B extends Type<B>> {

    class Left<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {
        Type<A> a;

        Left(Type<A> a) {
            this.a = a;
        }
    }

    class Right<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {
        Type<B> b;

        Right(Type<B> b) {
            this.b = b;
        }
    }
}
