package dev.austinzhu.algo.structure.map;

public sealed interface Map<K, V> permits ArrayMap, HashMap {

    K[] keys();

    V[] values();

    void insert(K key, V value);

    V get(K key);

    void set(K key, V value);

    sealed class Tuple<K, V> permits HashMap.Entry {

        K key;

        V value;

        public Tuple(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
