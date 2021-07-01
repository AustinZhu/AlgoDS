package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public final class Unit implements Type<Unit> {

    public static Unit unit() {
        return new Unit();
    }

    private Unit() {
    }
}
