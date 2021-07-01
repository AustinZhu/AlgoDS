package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Monad<M extends Type<M> & Functor<M, A> & Applicative<M, A> & Monad<M, A>, A extends Type<A>> {

    Monad<M, A> ret(A a);

    <B extends Type<B>, MB extends Monad<MB, B> & Type<MB> & Functor<MB, B> & Applicative<MB, B>, MF extends Monad<MF, Function<A, MB>> & Type<MF> & Functor<MF, Function<A, MB>> & Applicative<MF, Function<A, MB>>>
    Monad<MB, B> compose(Monad<M, A> m, Monad<MF, Function<A, MB>> f);
}
