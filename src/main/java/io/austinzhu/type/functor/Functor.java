package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Functor<A extends Type<A>> {
    <B extends Type<B>> Functor<B> fmap(Function<A, B> f, Functor<A> F);
}
