package Dictionary;

import Tree.BinarySearchTree;
import utils.Entry;

import java.util.Iterator;

public class TreeDictionary <K extends Comparable <? super K>, V> implements DictionaryInterface <K, V>{

    BinarySearchTree <Entry<K, V>> tree;


    public TreeDictionary(){
        tree = new BinarySearchTree<>();
    }

    @Override
    public V add(K key, V value) {
        System.out.println("here");

        Entry<K, V> entry1 = new Entry<>(key, value);
        tree.add(entry1);

        return value;
    }

    @Override
    public V remove(K key) {
        V value = getValue(key);
        if (value != null){
            tree.remove(new Entry<>(key, value));

        }
        return value;
    }


    public V getValue(K key) {
        Iterator<K> iteratorKey = getKeyIterator();
        Iterator<V> iteratorValue = getValueIterator();
        while(iteratorKey.hasNext()){
            if(iteratorKey.next().compareTo(key) == 0){
                return iteratorValue.next();
            }
            iteratorValue.next();
        }
        return  null;
    }




    @Override
    public boolean contains(K key) {
//        return tree.contains();

        return (getValue(key) != null);
    }

    @Override
    public int getSize() {
        return tree.getNumberOfNodes();
    }

    @Override
    public boolean isEmpty() {
        return (tree.getNumberOfNodes() == 0);
    }

    @Override
    public void clear() {
        tree.clear();
    }
    private class KeyIterator implements Iterator<K>{
        int cursor;
        Iterator<Entry<K, V>> iterator;
        public KeyIterator(){

            iterator = tree.getIterator();
        }
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator.next().getKey();
        }
    }
    private class ValueIterator implements Iterator<V>{

        Iterator<Entry<K, V>> iterator;
        public ValueIterator(){

          iterator = tree.getIterator();
        }
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public V next() {
            return iterator.next().getValue();
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

    public Iterator<Entry<K, V>> getIterator(){
        return tree.getIterator();
    }
}
