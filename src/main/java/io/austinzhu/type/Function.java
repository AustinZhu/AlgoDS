package io.austinzhu.type;

import java.util.Objects;

@FunctionalInterface
public interface Function<A extends Type<A>, B extends Type<B>> extends Type<Function<A, B>> {

    default Function<A, A> id() {
        return x -> x;
    }

    default <C extends Type<C>> Function<A, C> compose(Function<B, C> f) {
        Objects.requireNonNull(f);
        return (A a) -> f.apply(apply(a));
    }

    B apply(A a);
}
