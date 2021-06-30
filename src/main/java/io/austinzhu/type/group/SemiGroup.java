package io.austinzhu.type.group;

import io.austinzhu.type.Type;

public interface SemiGroup<A extends Type<A>> {
    A assoc(A a, A b);
}
