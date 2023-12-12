package List;
import utils.Node;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/474931/View
public class LList <T> extends BaseLink<T> implements ListInterface<T>{

    public LList(){
        super();
    }

    /**
     * add anEntry value to the top of the list
     *
     * @param newEntry
     */
    @Override
    public void add(T newEntry) {
        int n = getLength();
        add(newEntry, n);
    }

    /**
     * add newEntry value at the index idx and push the rest up
     *
     * @param newEntry
     * @param idx
     */
    @Override
    public void add(T newEntry, int idx) {

        if (idx < 0 || idx > getLength())
            throw new IndexOutOfBoundsException (
                    "wrong position for adding");
        if (idx == 0)
            addFirstNode (new Node <> (newEntry));
        else {
            Node <T> prevNode = getNodeAt (idx - 1);
            addNodeAfter (prevNode, new Node <>(newEntry));
        }

    }

    /**
     * return true if the given anEntry is remove from the list
     *
     * @param anEntry
     * @return T
     */
    @Override
    public boolean remove(T anEntry) {
        Node <T> prevNode = null, currNode = getFirstNode();
        while (currNode != null && !
                anEntry.equals(currNode.getData())) {
            prevNode = currNode; currNode = currNode.getNext();
        }
        if (prevNode == null) {
            removeFirstNode();
            return true;
        }
        if (currNode != null) {
            removeNodeAfter (prevNode);
            return true;
        }
        return false;
    }
    private class ListIterator implements Iterator<T> {

        int cursor;

        public ListIterator(){
            cursor = 0;
        }


        @Override
        public boolean hasNext() {
            return cursor < getLength();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T entry = getEntry(cursor);
            cursor++;
            return entry;
        }
    }

    public Iterator<T> getIterator(){
        return new ListIterator();
    }


    /**
     * clea the list by removing all entries
     */
    @Override
    public void clear() {
        if(getLength() == 0) throw new EmptyStackException();
        super.clear();

    }

    /**
     * replace the value at index idx with the given anEntry
     * return the value that was replace
     *
     * @param newEntry
     * @param idx
     * @return T
     */
    @Override
    public T replace(int idx,T newEntry) {
        Node <T> currNode = getNodeAt (idx);
        T outData = currNode.getData();
        currNode.setData(newEntry);
        return outData;
    }

    /**
     * does anEntry exist in the list
     *
     * @param anEntry
     * @return boolean
     */
    @Override
    public boolean contains(T anEntry) {
        for (Node <T> currNode = getFirstNode(); currNode != null;
             currNode = currNode.getNext())
            if (anEntry.equals (currNode.getData()))
                return true;
        return false;
    }

    private Node<T> getFirstNode() {
        return getNodeAt(0);
    }
}
