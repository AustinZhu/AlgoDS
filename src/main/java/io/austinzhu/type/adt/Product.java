package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

abstract class Product<A extends Type<A>, B extends Type<B>> {

    final A a;

    final B b;

    Product(A a, B b) {
        this.a = a;
        this.b = b;
    }
}
