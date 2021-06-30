package io.austinzhu.type.group;

import io.austinzhu.type.Type;

public interface Monoid<A extends SemiGroup<A> & Type<A>> {
    A unit();
}
