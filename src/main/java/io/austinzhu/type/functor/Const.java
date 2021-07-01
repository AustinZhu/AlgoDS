package io.austinzhu.type.functor;

import io.austinzhu.type.Type;
import io.austinzhu.type.bifunctor.Bifunctor;

public interface Const<
        A extends Type<A>,
        B extends Type<B>> extends Bifunctor<Const<A, B>, A, B>, Type<Const<A, B>> {

    A constant();
}
