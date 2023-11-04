package Queue;


import utils.EmptyQueueException;
import utils.Node;
public class LinkQueue<T> implements QueueInterface <T> {

    private Node <T> frontNode;
    private Node <T> backNode;

    public LinkQueue(){
        frontNode = null;
        backNode = null;
    }
    @Override
    public void enqueue(T anEntry) {
        Node <T> newNode = new Node <> (anEntry);
        if (isEmpty()) frontNode = newNode;
        else{
            backNode.setNext(newNode);
        }
        backNode = newNode;

    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new EmptyQueueException();
        T front = frontNode.getData();
        frontNode = frontNode.getNext();
        if (frontNode == null) backNode = null;
        return front;
    }

    @Override
    public T getFront() {
        if (isEmpty()) throw new EmptyQueueException();
        return frontNode.getData();
    }

    @Override
    public boolean isEmpty() {
        if(frontNode == null) return true;


        return(backNode == null);
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            dequeue();
        }

    }
}
