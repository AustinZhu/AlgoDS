package io.austinzhu.type.functor;

import io.austinzhu.type.Type;

public interface Compose<
        F extends Functor<F, G> & Type<F>,
        G extends Functor<G, A> & Type<G>,
        A extends Type<A>> {

    F compose();
}
