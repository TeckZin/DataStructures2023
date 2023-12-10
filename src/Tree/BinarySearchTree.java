package Tree;


import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable < ? super T >> extends BinaryTree<T> implements SearchTreeInterface<T> {

    public BinarySearchTree () {
        super();
    }

    public BinarySearchTree(T rootEntry){
        super(rootEntry);
    }
    @Override
    public boolean contains(T entry) {
        return false;
    }

    @Override
    public T getEntry(T entry) {
        return null;
    }

    @Override
    public T add(T newEntry) {
        return null;
    }

    @Override
    public T remove(T entry) {
        return null;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return null;
    }

    @Override
    public T getRootData() {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }




}
