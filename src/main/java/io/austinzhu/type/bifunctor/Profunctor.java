package io.austinzhu.type.bifunctor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Profunctor<A extends Type<A>, B extends Type<B>> {

    <A1 extends Type<A1>, B1 extends Type<B1>> Profunctor<A1, B1> dimap(Function<A1, A> f, Function<B, B1> g, Profunctor<A, B> F);

    <A1 extends Type<A1>> Profunctor<A1, B> lmap(Function<A1, A> f, Profunctor<A, B> F);

    <B1 extends Type<B1>> Profunctor<A, B1> rmap(Function<B, B1> g, Profunctor<A, B> F);
}
