package io.austinzhu.type.functor;

import io.austinzhu.type.Type;

public interface Compose<F extends Functor<G>, G extends Functor<A> & Type<G>, A extends Type<A>> {
    F compose();
}
