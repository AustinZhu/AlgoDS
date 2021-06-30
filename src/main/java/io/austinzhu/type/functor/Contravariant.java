package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Contravariant<A extends Type<A>> {
    <B extends Type<B>> Contravariant<B> contramap(Function<B, A> f, Contravariant<A> F);
}