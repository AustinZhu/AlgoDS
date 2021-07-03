package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public sealed interface Nat extends Type<Nat>, Sum<Unit, Nat> permits Nat.Zero, Nat.Succ {

    Nat ZERO = new Nat.Zero();

    static Nat succ(Nat n) {
        return new Nat.Succ(n);
    }

    final class Zero extends Sum.Inl<Unit, Nat> implements Nat {

        private Zero() {
            super(new Unit());
        }
    }

    final class Succ extends Sum.Inr<Unit, Nat> implements Nat {

        private Succ(Nat n) {
            super(n);
        }
    }

}
