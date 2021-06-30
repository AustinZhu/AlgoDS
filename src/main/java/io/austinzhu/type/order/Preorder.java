package io.austinzhu.type.order;

import io.austinzhu.type.Type;
import io.austinzhu.type.adt.Bool;

public interface Preorder<A extends Type<A>> {
    Bool le(A a, A b);
}
