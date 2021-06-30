package io.austinzhu.type.order;

import io.austinzhu.type.Type;
import io.austinzhu.type.adt.Bool;

public interface Equality<A extends Type<A>> {
    Bool equal(A a, A b);
}
