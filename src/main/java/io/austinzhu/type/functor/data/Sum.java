package io.austinzhu.type.functor.data;

import io.austinzhu.type.Type;
import io.austinzhu.type.functor.Functor;

public interface Sum<F extends Functor<A>, G extends Functor<A>, A extends Type<A>> {

    class InL<F extends Functor<A>, G extends Functor<A>, A extends Type<A>> implements Sum<F, G, A> {

        F f;

        InL(F f) {
            this.f = f;
        }
    }

    class InR<F extends Functor<A>, G extends Functor<A>, A extends Type<A>> implements Sum<F, G, A> {

        G g;

        InR(G g) {
            this.g = g;
        }
    }
}
