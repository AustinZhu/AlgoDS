package io.austinzhu.type.adt;

import io.austinzhu.type.Type;


interface Sum<A extends Type<A>, B extends Type<B>> {

    class Inl<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {

        A a;

        Inl(A a) {
            this.a = a;
        }
    }

    class Inr<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {

        B b;

        Inr(B b) {
            this.b = b;
        }
    }
}
