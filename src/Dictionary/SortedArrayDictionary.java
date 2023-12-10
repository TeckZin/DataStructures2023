package Dictionary;




import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import utils.Entry;

public class SortedArrayDictionary <K extends Comparable<? super K>, V> implements DictionaryInterface <K, V> {

    private Entry<K, V>[] entries;
    private  K key;
    private V value;
    private static final int DEFAULT_CAPACITY = 50;


    private int numberOfEntries;


    public SortedArrayDictionary(int capacity){
        numberOfEntries = 0;
        entries = (Entry<K, V>[]) new Entry[capacity];

    }

    public SortedArrayDictionary(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) throw new IllegalArgumentException();
        V oldValue = null;
        int keyIndex = locateIndex (key);
        if (keyIndex < numberOfEntries && key.compareTo(entries[keyIndex].getKey())== 0) {
            oldValue = entries[keyIndex].getValue();
            entries[keyIndex].setValue(value); // replacing
            numberOfEntries++;
        }
        else {
            makeRoom (keyIndex);
//            System.out.println("Here");
//            System.out.println(key.getClass());
//            System.out.println(value);
            entries[keyIndex] = new Entry(key, value); // adding
//            System.out.println(entries[keyIndex].getKey().getClass());
            ensureCapacity();
            numberOfEntries++;

        }
        return oldValue;
    }


    @Override
    public V remove(K key) {
        int index = locateIndex(key);
        if (index == numberOfEntries || key.compareTo (entries[index].getKey()) < 0) return null;
        V oldValue = entries[index].getValue();

        entries[index] = null;
        numberOfEntries--;
        removeGap (index);
        return oldValue;
    }

    @Override
    public V getValue(K key) {
        int index = locateIndex (key);
        if (index < numberOfEntries && key.compareTo(entries[index].getKey()) == 0) return entries [index].getValue();
        return null;
    }

    @Override
    public boolean contains(K key) {
        int low = 0, high = numberOfEntries - 1;
        int mid;
        int compValue;
        while (low <= high) {
            mid = (low + high)/2;
            compValue = key.compareTo (entries[mid].getKey());
            if (compValue == 0) return true;
            else if (compValue > 0) low = mid + 1;
            else  high = mid - 1;
        }
        return false;
    }

    @Override
    public int getSize() { //
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() { //
        return numberOfEntries == 0;
    }

    @Override
    public void clear() {
        Iterator<K> iterator = getKeyIterator();
        while(iterator.hasNext()){
            iterator.remove();
        }


    }

    private void removeGap(int index) {
        assert (index >= 0 && index < numberOfEntries);
        for (int newIndex = index; newIndex < numberOfEntries; newIndex++){
            entries[newIndex] = entries[newIndex+1];
        }

    }

    private void makeRoom(int keyIndex) {
        assert (keyIndex >= 0 && keyIndex <=
                numberOfEntries);
        for (int idx = numberOfEntries; idx > keyIndex; idx--)
            entries[idx] = entries[idx-1];


    }
//    public boolean getEntry (K key) { // binary search
//        int low = 0, high = numberOfEntries - 1;
//        int mid;
//        int compValue;
//        while (low <= high) {
//            mid = (low + high)/2;
//            compValue = key.compareTo (entries[mid].getKey());
//            if (compValue == 0) return entries[mid].getValue();
//            else if (compValue > 0) low = mid + 1;
//            else high = mid - 1;
//        }
//        return null;
//    }

   private class KeyIterator implements Iterator <K> {
        int cursor;

        public KeyIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < numberOfEntries;
        }

        @Override
        public K next() {
            if (!hasNext())
                throw new NoSuchElementException();
            K out = entries[cursor ++].getKey();
            return out;
        }
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }


   private class ValueIterator implements Iterator <V> {
        int cursor;

        public ValueIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < numberOfEntries;
        }

        @Override
        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            V out = entries[cursor++].getValue();
            return out;
        }
    }


    @Override
    public Iterator<V> getValueIterator() {

        return new ValueIterator();
    }
    private void ensureCapacity() {
        if (numberOfEntries == entries.length) {// array is full
            entries = Arrays.copyOf(entries, 2*numberOfEntries);
        }
    }
    private int locateIndex(K key){
        int index = 0;
        while ((index < numberOfEntries) &&
                key.compareTo(entries[index].getKey()) > 0)
            index ++;
        return index;

    }
}