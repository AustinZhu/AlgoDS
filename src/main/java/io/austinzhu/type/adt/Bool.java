package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Bool implements Sum<Unit, Unit>, Type<Bool> {

    static class Left implements Sum<Unit, Unit> {
        Type<Unit> t;

        Left(Type<Unit> a) {
            this.t = a;
        }
    }

    static class Right implements Sum<Unit, Unit> {
        Type<Unit> f;

        Right(Type<Unit> b) {
            this.f = b;
        }
    }
}
