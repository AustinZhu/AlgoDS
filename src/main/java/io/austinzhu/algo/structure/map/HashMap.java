package io.austinzhu.algo.structure.map;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.util.ArrayList;
import java.util.Random;

public final class HashMap<K, V> implements Map<K, V> {

    private final Entry<K, V>[] data;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.data = new Entry[capacity];
    }

    public static HashMap<String, Integer> init(int size, int bound, Random random) {
        var capacity = random.nextInt(size);
        HashMap<String, Integer> map = new HashMap<>(capacity);
        for (var i = 0; i < capacity; i++) {
            var generatedString = random.ints(48, 123)
                    .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            map.insert(generatedString, random.nextInt(bound));
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] keys = (K[]) new Object[data.length];
        for (var i = 0; i < data.length; ++i) {
            keys[i] = data[i].key;
        }
        return keys;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        ArrayList<V> values = new ArrayList<>();
        for (Entry<K, V> e : data) {
            while (e != null) {
                values.add(e.value);
                e = e.next;
            }
        }
        return values.toArray((V[]) new Object[0]);
    }

    @Override
    @Algorithm
    public void insert(K key, V value) {
        Entry<K, V> node = new Entry<>(key, value);
        int bucket = Math.abs(node.hash) % data.length;
        if (data[bucket] == null) {
            data[bucket] = node;
        } else {
            Entry<K, V> cur = data[bucket];
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    @Override
    public V get(K key) {
        int bucket = Math.abs(key.hashCode() % data.length);
        Entry<K, V> cur = data[bucket];
        while (cur != null) {
            if (cur.key == key) {
                return cur.value;
            }
            cur = cur.next;
        }
        throw new ElementNotFoundException("No such key");
    }

    @Override
    public void set(K key, V value) {
        int bucket = Math.abs(key.hashCode() % data.length);
        Entry<K, V> cur = data[bucket];
        while (cur != null) {
            if (cur.key == key) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }
    }

    @Algorithm
    public void delete(K key) {
        int bucket = Math.abs(key.hashCode() % data.length);
        if (data[bucket] == null) {
            throw new ElementNotFoundException("No such key");
        }
        Entry<K, V> cur = data[bucket];
        if (cur.key == key) {
            data[bucket] = cur.next;
            return;
        }
        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (Entry<K, V> e : data) {
            sb.append(e).append('\n');
        }
        return sb.toString();
    }

    static final class Entry<K, V> extends Tuple<K, V> {

        private final int hash;

        private Entry<K, V> next;

        private Entry(K key, V value) {
            super(key, value);
            this.hash = key.hashCode();
            this.next = null;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    "}" + " -> " +
                    next;
        }
    }
}
