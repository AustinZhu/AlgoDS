package io.austinzhu.type.functor;

import io.austinzhu.type.Type;

public interface Identity<A extends Type<A>> extends Functor<Identity<A>, A>, Type<Identity<A>> {
    A id();
}