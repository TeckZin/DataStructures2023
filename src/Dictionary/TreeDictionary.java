package Dictionary;

import Tree.BinarySearchTree;
import utils.Entry;

import java.util.Iterator;

public class TreeDictionary <K extends Comparable <? super K>, V> implements DictionaryInterface{

    private BinarySearchTree<Entry<K, V>> tree;
    @Override
    public Object add(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object getValue(Object key) {
        return null;
    }

    @Override
    public boolean contains(Object key) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator getKeyIterator() {
        return null;
    }

    @Override
    public Iterator getValueIterator() {
        return null;
    }
}
