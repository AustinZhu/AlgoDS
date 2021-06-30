package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public class Pair<A extends Type<A>, B extends Type<B>> extends Product<A, B> {

    Pair(Type<A> a, Type<B> b) {
        super(a, b);
    }
}
