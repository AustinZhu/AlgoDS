package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Monad<A extends Type<A>> extends Applicative<A>, Type<Monad<A>> {

    Monad<A> ret(A a);

    <B extends Type<B>> Monad<B> compose(Monad<A> m, Monad<Function<A, Monad<B>>> f);
}
