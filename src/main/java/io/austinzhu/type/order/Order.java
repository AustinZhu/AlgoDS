package io.austinzhu.type.order;

import io.austinzhu.type.Type;
import io.austinzhu.type.adt.Bool;

public interface Order<A extends Preorder<A> & Equality<A> & Type<A>> {
    Bool leq(A a, A b);
}
