package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public interface Bool extends Sum<Unit, Unit>, Type<Bool> {

    Bool TRUE = new True();

    Bool FALSE = new False();

    class True extends A<Unit, Unit> implements Bool {

        private True() {
            super(new Unit());
        }
    }

    class False extends B<Unit, Unit> implements Bool {

        private False() {
            super(new Unit());
        }
    }
}
