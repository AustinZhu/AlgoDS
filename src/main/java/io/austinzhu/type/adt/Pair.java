package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Pair<A extends Type<A>, B extends Type<B>>
        extends Product<A, B>
        implements Type<Pair<A, B>> {

    public static <A extends Type<A>, B extends Type<B>> Pair<A, B> pair(A a, B b) {
        return new Pair<>(a, b);
    }

    private Pair(A a, B b) {
        super(a, b);
    }
}
