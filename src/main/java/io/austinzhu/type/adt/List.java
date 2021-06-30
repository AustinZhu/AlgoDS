package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public class List<A extends Type<A>> implements Sum<Unit, Product<A, List<A>>>, Type<List<A>> {

    class Left implements Sum<Unit, Product<A, List<A>>> {
        Type<Unit> n;

        Left(Type<Unit> a) {
            this.n = a;
        }
    }

    class Right implements Sum<Unit, Product<A, List<A>>> {
        Type<Product<A, List<A>>> c;

        Right(Product<A, List<A>> b) {
            this.c = b;
        }
    }
}
