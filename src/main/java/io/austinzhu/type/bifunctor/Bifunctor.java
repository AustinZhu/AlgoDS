package io.austinzhu.type.bifunctor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Bifunctor<A extends Type<A>, B extends Type<B>> {

    <A1 extends Type<A1>, B1 extends Type<B1>> Bifunctor<A1, B1> bimap(Function<A, A1> f, Function<B, B1> g, Bifunctor<A, B> F);

    <A1 extends Type<A1>> Bifunctor<A1, B> first(Function<A, A1> f, Bifunctor<A, B> F);

    <B1 extends Type<B1>> Bifunctor<A, B1> second(Function<B, B1> g, Bifunctor<A, B> F);
}
