package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Applicative<A extends Type<A>> extends Functor<A> {

    Applicative<A> pure(A a);

    <B extends Type<B>> Applicative<B> apply(Applicative<Function<A, B>> f, Applicative<A> a);
}
