package Stack;

import java.util.EmptyStackException;
public class ArrayStack <T> implements StackInterface<T>{
    private T[] stackArray;
    private int stackLength;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack(int capacity){
        this.capacity = capacity;
        this.stackArray = (T[]) new Object[capacity];
        this.stackLength = 0;

    }

    public ArrayStack(){
        this.stackArray = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.stackLength = 0;
    }

    /**
     * adds new entry to the top of the stack
     *
     * @param anEntry
     * @author Teck
     */
    @Override
    public void push(T anEntry) {
//        System.out.println("Push");
        if(capacity == stackLength-1) throw new ArrayIndexOutOfBoundsException("ArrayFull");
        stackArray[stackLength] = anEntry;
        stackLength++;

    }

    /**
     * return the top entry and remove it if not empty
     *
     * @return the top entry
     * @throws EmptyStackException
     * @author Teck
     */
    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty()) throw new EmptyStackException();
        stackLength--;
        T value = stackArray[stackLength];
        stackArray[stackLength] = null;


        return value;

    }

    /**
     * return the top entry without removing it and not empty
     *
     * @return top entry
     * @throws EmptyStackException
     * @author Teck
     */
    @Override
    public T peek() throws EmptyStackException {

        if(isEmpty()) throw new EmptyStackException();
        stackLength--;
        T value = stackArray[stackLength];
        stackLength++;
        return value;
    }

    /**
     * check if the stack is empty will be use for pop and peek
     *
     * @return if the stack is empty
     * @author Teck
     */
    @Override
    public boolean isEmpty() {
        if(stackLength == 0){
            return true;
        }
        for(T x: stackArray){
            if(x != null) return false;


        }
        return true;
    }

    /**
     * removes all entry in the stack
     *
     * @author Teck
     */
    @Override
    public void clear() {
        while(!isEmpty()){
            pop();
        }

    }


}
