package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public sealed interface List<A extends Type<A>>
        extends Sum<Unit, Pair<A, List<A>>>, Type<List<A>>
        permits List.Nil, List.Cons {

    final class Nil<A extends Type<A>> extends Sum.A<Unit, Pair<A, List<A>>> implements List<A> {

        public static <A extends Type<A>> List<A> nil() {
            return new Nil<>();
        }

        private Nil() {
            super(Unit.unit());
        }
    }

    final class Cons<A extends Type<A>> extends B<Unit, Pair<A, List<A>>> implements List<A> {

        public static <A extends Type<A>> List<A> cons(A a, List<A> l) {
            return new Cons<>(Pair.pair(a, l));
        }

        private Cons(Pair<A, List<A>> b) {
            super(b);
        }
    }
}
