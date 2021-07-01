package io.austinzhu.type;

import io.austinzhu.type.functor.Applicative;
import io.austinzhu.type.functor.Functor;

public interface Function<A extends Type<A>, B extends Type<B>> extends Type<Function<A, B>>, Functor<Function<A, B>, B>, Applicative<Function<A, B>, B> {

    Function<A, A> id();

    <C extends Type<C>> Function<A, C> compose(Function<? super A, ? extends C> g);

    B apply(A a);
}
