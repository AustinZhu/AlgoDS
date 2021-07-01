package io.austinzhu.type.functor.data;

import io.austinzhu.type.Type;
import io.austinzhu.type.functor.Functor;

public class Product<
        F extends Functor<F, A> & Type<F>,
        G extends Functor<G, A> & Type<G>,
        A extends Type<A>> {

    F f;

    G g;

    Product(F f, G g) {
        this.f = f;
        this.g = g;
    }
}
