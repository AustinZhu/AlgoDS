package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Functor<
        FA extends Type<FA> & Functor<FA, A>,
        A extends Type<A>> {

    <B extends Type<B>, FB extends Functor<FB, B> & Type<FB>>
    FB fmap(Function<A, B> f, FA fa);
}
