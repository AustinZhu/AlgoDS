package io.austinzhu.type;

public interface Function<A extends Type<A>, B extends Type<B>> extends Type<Function<A, B>> {

    Function<A, A> id();

    <C extends Type<C>> Function<A, C> compose(Function<B, C> g, Function<A, B> f);
}
