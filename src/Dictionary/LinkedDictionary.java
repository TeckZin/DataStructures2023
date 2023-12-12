package Dictionary;

import List.BaseLink;
import List.LList;
import utils.Entry;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>{

    LList<Entry<K, V>> entries;

    public LinkedDictionary(){
        entries = new LList<Entry<K, V>>();
    }


    @Override
    public V add(K key, V value) {
        Entry<K, V> entry = new Entry(key, value);
        entries.add(entry);
        return value;
    }

    @Override
    public V remove(K key) {
        Iterator<Entry<K, V>> entryIterator = entries.getIterator();
        while(entryIterator.hasNext()){
            Entry<K, V> entry = entryIterator.next();
            if(entry.getKey().equals(key)){
                entries.remove(entry);
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public V getValue(K key) {
        Iterator<K> keyIterator = getKeyIterator();
        Iterator<V> valueIterator = getValueIterator();
        while(keyIterator.hasNext()){
            if(key.hashCode() == keyIterator.next().hashCode()){
                return valueIterator.next();
            }
            valueIterator.next();
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return (getValue(key) != null);
    }

    @Override
    public int getSize() {
        return entries.getLength();
    }

    @Override
    public boolean isEmpty() {
        return (getSize() == 0);
    }

    @Override
    public void clear() {
        Iterator<Entry<K, V> >entryIterator = entries.getIterator();
        while( entryIterator.hasNext()){
            entries.remove(entryIterator.next());
        }

    }

    private class ValueIterator implements  Iterator<V> {
        Iterator<Entry<K, V>> iterator;
        public ValueIterator(){
            iterator = entries.getIterator();
        }


        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public V next() {
            if(!hasNext()) throw new NoSuchElementException();

            return iterator.next().getValue();
        }
    }

    private class KeyIterator implements Iterator<K> {

        Iterator<Entry<K, V>> iterator;

        public KeyIterator(){
             iterator = entries.getIterator();

        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            if(!hasNext()) throw new NoSuchElementException();
            return iterator.next().getKey();
        }
    }

    @Override
    public Iterator<K> getKeyIterator() {



        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }
}
