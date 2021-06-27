package io.austinzhu.algo.structure.map;

import io.austinzhu.algo.exception.ElementNotFoundException;
import io.austinzhu.algo.interfaces.Algorithm;

import java.lang.reflect.Array;
import java.util.Random;

public class HashMap<K, V> {

    private class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.hash = key.hashCode();
            this.key = key;
            this.value = value;
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

    private Entry<K, V>[] data;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.data = (Entry<K, V>[]) Array.newInstance(Entry.class, capacity);
    }

    public static HashMap<String, Integer> init(int size, int bound) {
        Random random = new Random();
        int capacity = random.nextInt(size);
        HashMap<String, Integer> map = new HashMap<>(capacity);

        for (int i = 0; i < capacity; i++) {
            String generatedString = random.ints(48, 123)
                    .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            map.insert(generatedString, random.nextInt(bound));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] keys = (K[]) new Object[data.length];
        for (int i = 0; i < data.length; ++i) {
            keys[i] = data[i].key;
        }
        return keys;
    }

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

    @Algorithm
    public void delete(K key) {
        int bucket = Math.abs(key.hashCode()) % data.length;
        if (data[bucket] == null) {
            throw new ElementNotFoundException("No such key");
        } else {
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
    }

    public V get(K key) {
        int bucket = Math.abs(key.hashCode()) % data.length;
        if (data[bucket] != null) {
            Entry<K, V> cur = data[bucket];
            if (cur.key == key) {
                data[bucket] = cur.next;
                return cur.value;
            }
            while (cur.next != null) {
                if (cur.next.key == key) {
                    return cur.next.value;
                } else {
                    cur = cur.next;
                }
            }
        }
        throw new ElementNotFoundException("No such key");
    }

    @Override
    public String toString() {
        if (data == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<K,V> e : data) {
            sb.append(e).append('\n');
        }
        return sb.toString();
    }
}
