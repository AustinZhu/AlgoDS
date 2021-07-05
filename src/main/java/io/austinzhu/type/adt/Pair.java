package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Pair<A extends Type<A>, B extends Type<B>>
        extends Product<A, B>
        implements Type<Pair<A, B>> {

    public Pair(A a, B b) {
        super(a, b);
    }

    static <A extends Type<A>, B extends Type<B>> A fst(Product<A, B> x) {
        return x.a;
    }

    static <A extends Type<A>, B extends Type<B>> B snd(Product<A, B> x) {
        return x.b;
    }
}
