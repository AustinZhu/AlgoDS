package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Maybe<A extends Type<A>> implements Sum<Unit, A>, Type<Maybe<A>> {

    class Left implements Sum<Unit, A> {
        Type<Unit> n;

        Left(Type<Unit> a) {
            this.n = a;
        }
    }

    class Right implements Sum<Unit, A> {
        Type<A> a;

        Right(Type<A> b) {
            this.a = b;
        }
    }
}
