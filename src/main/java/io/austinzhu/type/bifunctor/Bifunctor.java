package io.austinzhu.type.bifunctor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Bifunctor<
        F extends Bifunctor<F, A, B> & Type<F>,
        A extends Type<A>,
        B extends Type<B>> {

    <G extends Bifunctor<G, A1, B1> & Type<G>, A1 extends Type<A1>, B1 extends Type<B1>>
    Bifunctor<G, A1, B1> bimap(Function<A, A1> f, Function<B, B1> g, Bifunctor<F, A, B> fab);

    <G extends Bifunctor<G, A1, B> & Type<G>, A1 extends Type<A1>>
    Bifunctor<G, A1, B> first(Function<A, A1> f, Bifunctor<F, A, B> fab);

    <G extends Bifunctor<G, A, B1> & Type<G>, B1 extends Type<B1>>
    Bifunctor<G, A, B1> second(Function<B, B1> g, Bifunctor<F, A, B> fab);
}
