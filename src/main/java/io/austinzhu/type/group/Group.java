package io.austinzhu.type.group;

import io.austinzhu.type.Type;

public interface Group<A extends Monoid<A> & Type<A> & SemiGroup<A>> {
    A invert(A a);
}
