package io.austinzhu.type.adt;

import io.austinzhu.type.Function;
import io.austinzhu.type.Type;
import io.austinzhu.type.functor.Functor;

public sealed interface Maybe<A extends Type<A>>
        extends Sum<Unit, A>, Type<Maybe<A>>, Functor<Maybe<A>, A>
        permits Maybe.Nothing, Maybe.Just {

    final class Nothing<A extends Type<A>> extends Sum.A<Unit, A> implements Maybe<A> {

        public static <A extends Type<A>> Maybe<A> nothing() {
            return new Nothing<>();
        }

        private Nothing() {
            super(Unit.unit());
        }
    }

    final class Just<A extends Type<A>> extends Sum.B<Unit, A> implements Maybe<A> {

        public static <A extends Type<A>> Maybe<A> just(A a) {
            return new Just<>(a);
        }

        private Just(A b) {
            super(b);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default <B extends Type<B>, FB extends Functor<FB, B> & Type<FB>> FB fmap(Function<A, B> f, Maybe<A> fa) {
        if (fa instanceof Nothing) {
            return (FB) new Nothing<B>();
        }
        if (fa instanceof Just<A> j) {
            return (FB) new Just<>(f.apply(j.b));
        }
        throw new ClassCastException();
    }
}
