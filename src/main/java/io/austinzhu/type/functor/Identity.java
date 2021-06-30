package io.austinzhu.type.functor;

import io.austinzhu.type.Type;

public interface Identity<A extends Type<A>> extends Functor<A> {
    A id();
}