package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public abstract class Product<A extends Type<A>, B extends Type<B>> implements Type<Product<A, B>> {

    Type<A> a;

    Type<B> b;

    Product(Type<A> a, Type<B> b) {
        this.a = a;
        this.b = b;
    }

    public Type<A> fst() {
        return a;
    }

    public Type<B> snd() {
        return b;
    }
}
