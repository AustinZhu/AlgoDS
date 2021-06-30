package io.austinzhu.algo.structure.map;

public sealed interface Map<K, V> permits HashMap {

    K[] keys();

    V[] values();

    void insert(K key, V value);

    V get(K key);

    void set(K key, V value);

    class Tuple<K, V> {

        protected K key;

        protected V value;

        public Tuple(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
