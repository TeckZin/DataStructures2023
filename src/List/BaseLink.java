package List;
import utils.Node;

import java.util.EmptyStackException;

// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/474930/View
public abstract class BaseLink <T>{

    private Node <T> head;
    private int numberOfEntries;
    public BaseLink () {
        initializeDataFields ();
    }

    protected final void initializeDataFields () {
        head = null;
        numberOfEntries = 0;
    }

    protected final void addFirstNode (Node <T> toAdd) {
        toAdd.setNext (head);
        head = toAdd;
        numberOfEntries ++;
    }

    protected final void addNodeAfter (Node <T> currNode, Node <T> toAdd) {
        toAdd.setNext (currNode.getNext());
        currNode.setNext (toAdd);
        numberOfEntries ++;
    }

    protected final Node<T> getNodeAt (int position) {
        if (position < 0 || position >= numberOfEntries)
            throw new IndexOutOfBoundsException("Searching outside the chain");
        int index = 0;
        Node <T> currNode = head;
        while (index < position) {
            currNode = currNode.getNext();
            index ++;
        }
        return currNode;
    }

    public boolean isEmpty () {
        if (numberOfEntries == 0 ^ head == null)
            throw new IllegalStateException ("Corrupted chain");
        return head == null;
    }
    public Object [] toArray() {
        Object [] result = new Object[numberOfEntries];
        Node <T> currNode = head;
        for (int idx = 0; idx < numberOfEntries; idx ++) {
            result[idx] = currNode.getData();
            currNode = currNode.getNext();
        }
        return result;
    }

    public int getLength() {
        return numberOfEntries;
    }
    public void clear() {
        numberOfEntries = 0;
        head = null;
    }

    public T getEntry (int givenPosition) {
        return getNodeAt(givenPosition).getData();
    }

    public T remove (int givenPosition) {
        Node <T> toRemove;
        if (givenPosition == 0)
            toRemove = removeFirstNode();
        else {
            Node <T> prevNode = getNodeAt(givenPosition - 1);
            toRemove = removeNodeAfter(prevNode);
        }
        return toRemove.getData();
    }
    public Node<T> removeFirstNode(){
        if(numberOfEntries == 0) throw new EmptyStackException();
        Node<T> firstNode = getNodeAt(0);
        firstNode.setNext(null);
        return firstNode;
    }

    public  Node<T> removeNodeAfter(Node<T> anEntry){
        if(numberOfEntries == 1) throw new IndexOutOfBoundsException("removeNodeAfter fail");
        Node<T> nextNode = anEntry.getNext();
        anEntry.setNext(null);
        return nextNode;
    }






}