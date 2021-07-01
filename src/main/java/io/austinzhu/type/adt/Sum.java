package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public interface Sum<A extends Type<A>, B extends Type<B>> {

    class A<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {
        public A a;

        public A(A a) {
            this.a = a;
        }
    }

    class B<A extends Type<A>, B extends Type<B>> implements Sum<A, B> {
        public B b;

        public B(B b) {
            this.b = b;
        }
    }
}
