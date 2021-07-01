package io.austinzhu.type.adt;

import io.austinzhu.type.Type;

public interface Bool extends Sum<Unit, Unit>, Type<Bool> {

    class True extends A<Unit, Unit> implements Bool {

        public static Bool true_() {
            return new True();
        }

        private True() {
            super(Unit.unit());
        }
    }

    class False extends B<Unit, Unit> implements Bool {

        public static Bool false_() {
            return new False();
        }

        private False() {
            super(Unit.unit());
        }
    }
}
