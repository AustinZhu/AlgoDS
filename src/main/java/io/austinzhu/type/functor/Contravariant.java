package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Contravariant<
        FA extends Contravariant<FA, A>,
        A extends Type<A>> {

    <B extends Type<B>, FB extends Contravariant<FB, B>>
    Contravariant<FB, B> contramap(Function<B, A> f, Contravariant<FA, A> fa);
}