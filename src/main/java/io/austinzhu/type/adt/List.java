package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public sealed interface List<A extends Type<A>>
        extends Sum<Unit, Pair<A, List<A>>>, Type<List<A>>
        permits List.Nil, List.Cons {

    List<?> NIL = new Nil<>();

    static <A extends Type<A>> List<A> cons(A x, List<A> xs) {
        return new Cons<>(new Pair<>(x, xs));
    }

    final class Nil<A extends Type<A>> extends Sum.Inl<Unit, Pair<A, List<A>>> implements List<A> {

        private Nil() {
            super(new Unit());
        }

        @Override
        public String toString() {
            return "[]";
        }
    }

    final class Cons<A extends Type<A>> extends Sum.Inr<Unit, Pair<A, List<A>>> implements List<A> {

        private Cons(Pair<A, List<A>> xs) {
            super(xs);
        }

        @Override
        public String toString() {
            return null;
        }
    }
}
