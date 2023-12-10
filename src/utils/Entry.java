package utils;

public class Entry <K, V> implements Comparable<Entry<K, V>> {
    private K key;
    private V value;
    public Entry (K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public void setValue (V newValue) {
        value = newValue;
    }


    @Override
    public int compareTo(Entry<K, V> o) {
        return 0;
    }
}
