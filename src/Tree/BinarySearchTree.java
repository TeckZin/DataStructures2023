package Tree;



import Dictionary.BusinessName;
import Stack.LinkedStack;
import utils.Entry;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.EmptyStackException;
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
        return (getEntry(entry) != null) ;
    }

    @Override
    public T getEntry(T entry) {
        return findEntry(getRootNode(), entry);
    }
    private T addEntry ( BinaryNode <T> rootNode, T newEntry){
        System.out.println(newEntry.getClass());
        assert (rootNode != null);
        T result = null;
        int compValue = newEntry.compareTo(rootNode.getData());
        System.out.println("In AddEntry");
        System.out.println(newEntry.hashCode());
        System.out.println(rootNode.getData().hashCode());
        if (compValue == 0) {

            result = rootNode.getData();
            rootNode.setData(newEntry);
        } else if (compValue < 0){
            if (rootNode.hasLeftChild())
                result = addEntry (rootNode.getLeftChild(), newEntry);
            else
                rootNode.setLeftChild(new BinaryNode <T>(newEntry));
        }
        else {

            if (rootNode.hasRightChild()) result = addEntry (rootNode.getRightChild(), newEntry);
            else rootNode.setRightChild(new BinaryNode <T>(newEntry));
        } // end if
        return result;
    }

    @Override
    public T add(T newEntry) {
        T result = null;

        System.out.println("Test");
        if (isEmpty()) {
            System.out.println("new");
            setTree(new BinaryNode<T>(newEntry));
        } else{
            System.out.println(getRootNode().getData().hashCode());
            System.out.println(newEntry.hashCode());
            result = addEntry (getRootNode(), newEntry);
        }


        return result;
    }
    private T findEntry (BinaryNode <T> rootNode, T entry ){
        T result = null;
        if (rootNode != null) {
            T rootEntry = rootNode.getData();
            int comp = entry.compareTo (rootEntry);
            if (comp == 0)
                result = rootEntry;
            else if (comp < 0)
                result = findEntry (rootNode.getLeftChild(), entry) ;
            else
                result = findEntry (rootNode.getRightChild(), entry) ;
        }
        return result;
    }

    public class MoveInfo {
        private BinaryNode<T> parent;
        private BinaryNode<T> current;
        private boolean left;

        public MoveInfo() {
            parent = null;
            current = getRootNode();
            left = true;
        }

        public void setParent(BinaryNode<T> parent) {
            this.parent = parent;
        }

        public BinaryNode<T> getParent() {
            return parent;
        }

        public void setCurrent(BinaryNode<T> node) {
            current = node;
        }

        public BinaryNode<T> getCurrent() {
            return current;
        }

        public void setLeft() {
            left = true;
        }

        public void setRight() {
            left = false;
        }

        public boolean getLeft() {
            return left;
        }
    }
    @Override
    public T remove(T entry) {
        T result;
        if (isEmpty())  return null;
        MoveInfo move = new MoveInfo ();
        BinaryNode <T> currNode = getRootNode();
        T rootData;
        int compValue;
        do {
            rootData = move.getCurrent().getData();
            compValue = entry.compareTo (rootData);
            if (compValue > 0 ) { // move to the right
                move.setParent(currNode);
                currNode = currNode.getRightChild();
                move.setCurrent(currNode);
                move.setRight ();
            }  else if (compValue < 0) { // move to the left
                move.setParent(currNode);
                currNode = currNode.getLeftChild();
                move.setCurrent(currNode);
                move.setLeft();
            }
        } while (compValue != 0 && currNode != null);
        if (currNode == null) return null;
        assert (compValue == 0);
        result = currNode.getData();
        boolean hasLeft = currNode.hasLeftChild();
        boolean hasRight = currNode.hasRightChild();
        if (!hasLeft ) { // hasRight or leaf
            resetChild (move, currNode.getRightChild());
        }
        else if (!hasRight) {
            resetChild (move, currNode.getLeftChild());
        }else { // two children
            BinaryNode <T> toRemove = currNode;
            move.setParent(currNode);
            currNode = currNode.getLeftChild();
            move.setLeft();
            move.setCurrent(currNode);
            while (currNode.hasRightChild()) {
                move.setRight();
                move.setParent(currNode);
                currNode = currNode.getRightChild();
                move.setCurrent(currNode);
            }
            toRemove.setData(currNode.getData());
            resetChild (move, currNode.getLeftChild());
        }
        return result;
    }
    public T addIterative (T newEntry) {
        if (isEmpty()) {
            setTree(new BinaryNode<>(newEntry));
            return null;
        }


        BinaryNode<T> currNode = getRootNode();
        T oldValue = null;
        while (true) {
            oldValue = currNode.getData();
            int compValue = newEntry.compareTo(oldValue);
            if (compValue == 0) {
                currNode.setData(newEntry);
                return oldValue;
            }
            if (compValue < 0) {
                if (currNode.hasLeftChild())
                    currNode = currNode.getLeftChild();
                else {
                    currNode.setLeftChild(new BinaryNode<>(newEntry));
                    return null;
                }
            } else {
                if (currNode.hasRightChild())
                    currNode = currNode.getRightChild();
                else {
                    currNode.setRightChild(new BinaryNode<>(newEntry));
                    return null;
                }
            }
        }
    }
    private void resetChild (MoveInfo move, BinaryNode <T>
            newChild){
        BinaryNode <T> parent = move.getParent();
        if (parent == null ) // root: the only node with no parent
            setTree (newChild);
        else if (move.getLeft())
            parent.setLeftChild (newChild);
        else
            parent.setRightChild (newChild);
    }



    public Iterator<T> getIterator() {
        return getInorderIterator();
    }

}
