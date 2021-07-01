package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public abstract class Product<A extends Type<A>, B extends Type<B>> {

    private final A a;

    private final B b;

    Product(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A fst() {
        return a;
    }

    public B snd() {
        return b;
    }
}
