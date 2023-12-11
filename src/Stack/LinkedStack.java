package Stack;

import utils.Node;


import java.util.EmptyStackException;
public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;

    public LinkedStack(){
        topNode = null;
    }



    /**
     * adds new entry to the top of the stack
     *
     * @param anEntry
     * @author Teck
     */
    @Override
    public void push(T anEntry) {
        Node <T> dataNode = new Node<>(anEntry);
        dataNode.setNext(topNode);
        topNode = dataNode;


        

    }

    /**
     * return the top entry and remove it if not empty
     *
     * @return the top entry
     * @throws EmptyStackException
     * @author Teck
     */
    @Override
    public T pop() {
        if(isEmpty()) throw new EmptyStackException();
        T outData = (T) topNode.getData();
        topNode = topNode.getNext();
        return outData;


    }

    /**
     * return the top entry without removing it and not empty
     *
     * @return top entry
     * @throws EmptyStackException
     * @author Teck
     */
    @Override
    public T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return (T) topNode.getData();

    }

    /**
     * check if the stack is empty will be use for pop and peek
     *
     * @return if the stack is empty
     * @author Teck
     */
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    /**
     * removes all entry in the stack
     *
     * @author Teck
     */
    @Override
    public void clear() {
        topNode = null;

    }


}
