package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public sealed interface List<A extends Type<A>>
        extends Sum<Unit, Pair<A, List<A>>>, Type<List<A>>
        permits List.Nil, List.Cons {

    List NIL = new Nil<>();

    static <A extends Type<A>> List<A> cons(A a, List<A> l) {
        return new Cons<>(new Pair<>(a, l));
    }

    final class Nil<A extends Type<A>> extends Sum.A<Unit, Pair<A, List<A>>> implements List<A> {

        private Nil() {
            super(new Unit());
        }

        @Override
        public String toString() {
            return "[]";
        }
    }

    final class Cons<A extends Type<A>> extends Sum.B<Unit, Pair<A, List<A>>> implements List<A> {

        private Cons(Pair<A, List<A>> b) {
            super(b);
        }

        @Override
        public String toString() {
            return null;
        }
    }
}
