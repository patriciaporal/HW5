package space.harbour.java.hw5;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    public static class Pair<K, V> {
        private K key;
        private V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private int bucketSize = 16;
    private LinkedList<Pair<K, V>>[] buckets = new LinkedList[bucketSize];

    public MyHashMap() {
        clear();
    }

    @Override
    public int size() {
        int result = 0;

        for (int i = 0; i < buckets.length; i++) {
            result += buckets[i].size();
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int keyToBucketIndex(Object key) {
        return Math.abs(key.hashCode() % bucketSize);
        //return key.hashCode() >> 27;
    }

    @Override
    public boolean containsKey(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) return true;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        int i = keyToBucketIndex(value);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.value.equals(value)) return true;
        }

        return false;
    }

    @Override
    public V get(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) return pair.value;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        int i = keyToBucketIndex(key);

        if (buckets[i].contains(pair)) {
            buckets[i].set(i, pair);
        } else {
            buckets[i].add(pair);
        }

        return value;
    }

    @Override
    public V remove(Object key) {
        int i = keyToBucketIndex(key);
        if (buckets[i] != null) {
            LinkedList<Pair<K, V>> pair = buckets[i];
            for (Pair<K, V> entry: pair)
                if (entry.key.equals(key)) {
                    pair.remove(entry);
                }
        }

        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Iterator<? extends K> iterator = m.keySet().iterator();
        while (iterator.hasNext()) {
            K key = iterator.next();
            put(key, m.get(key));
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();

        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                LinkedList<Pair<K, V>> pair = buckets[i];
                for (Pair<K, V> entry: pair)
                    set.add(entry.getKey());
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Pair<K, V>> set = new HashSet<>();

        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                LinkedList<Pair<K, V>> pair = buckets[i];
                for (Pair<K, V> entry: pair)
                    set.add(entry);
            }
        }
        return null;
    }

    @Override
    public V replace(K key, V value) {
        int i = keyToBucketIndex(key);
        LinkedList<Pair<K, V>> pair = buckets[i];
        for (Pair<K, V> entry : pair)
            if (entry.getKey().equals(key)) {
              V oldValue = entry.getValue();
              entry.value = value;
              return oldValue;
            }
        return null;
    }
}
