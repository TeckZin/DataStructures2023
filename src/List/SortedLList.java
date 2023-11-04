package List;

import utils.Node;
// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/475391/View
public class SortedLList <T extends Comparable <? super T>> extends BaseLink <T> implements SortedListInterface <T> {
    public SortedLList() {
        super ();
    }
    private Node <T> getNodeBefore (T anEntry) {
        Node <T> currNode = getFirstNode();
        Node <T> nodeBefore = null;
        while (currNode != null && anEntry.compareTo (currNode.getData()) > 0){
            nodeBefore = currNode;
            currNode = currNode.getNext();
        }
        return nodeBefore;
    }

    public int getPosition( T anEntry) {
        int position = 0;
        int length = getLength();
        Node <T> currNode = getFirstNode();
        int compValue = 0;
        while (position < length) {
            compValue = anEntry.compareTo (currNode.getData());
            if (compValue > 0) {
                position ++;
                currNode = currNode.getNext();
            }
            else
                break;
        }
        if (position >= length || compValue != 0)
            position = -1 - position;
        return position;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    public void addEntry(T newEntry) {
        Node <T> before = getNodeBefore (newEntry);
        Node <T> toInsert = new Node <> (newEntry);
//        System.out.println("here rn");
        if (before == null){
            addFirstNode (toInsert);
            return;

        }else{
            addNodeAfter(before, toInsert);
        }



    }

    public boolean removeEntry (T anEntry) {
        if (isEmpty())
            return false;
        Node <T> before = getNodeBefore (anEntry);
        if (before == null) {
            if (anEntry.equals(getFirstNode().getData())) {
                removeFirstNode();
                return true;
            }
            return false;
        }
        Node <T> nextNode = before.getNext();
        if (nextNode == null) // anEntry is greater than all
            return false;
        if (anEntry.equals(nextNode.getData())) {
            removeNodeAfter(before);
            return true;
        }
        return false;
    }

    private Node<T> getFirstNode() {
        if(getLength() == 0)return null;

        return getNodeAt(0);
    }


}
