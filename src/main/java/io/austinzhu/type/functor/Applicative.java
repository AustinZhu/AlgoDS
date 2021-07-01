package io.austinzhu.type.functor;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;

public interface Applicative<
        FA extends Type<FA> & Functor<FA, A> & Applicative<FA, A>,
        A extends Type<A>> {

    Applicative<FA, A> pure(A a);

    <FB extends Applicative<FB, B> & Type<FB> & Functor<FB, B>, B extends Type<B>>
    Applicative<FB, B> apply(Applicative<Function<A, B>, B> f, Applicative<FA, A> fa);
}
