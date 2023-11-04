package List;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.concurrent.RejectedExecutionException;
// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/468796/View
public class AList<T> implements ListInterface <T> {
    private T[] list;
    private int numberOfEntries;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 10000;

    public AList(int capacity){
        if(DEFAULT_CAPACITY < capacity && capacity < MAX_CAPACITY) this.capacity = capacity;
        else if (MAX_CAPACITY < capacity) this.capacity = MAX_CAPACITY;
        else this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Object[this.capacity];
        numberOfEntries = 0;

    }

    public AList(){
        this(DEFAULT_CAPACITY);
    }
    private void checkCapacity(int capacity){
        if (capacity >= MAX_CAPACITY) throw new RejectedExecutionException("MAX CAP REACH");


    }
    private void ensureCapacity(){
        if (isFull()) {
            capacity *= 2;
            checkCapacity (capacity);
            list = Arrays.copyOf(list, capacity);
        }
    }

    private void makeRoom (int newPosition){
        assert (newPosition >= 0 && newPosition <=
                numberOfEntries);
        for (int idx = numberOfEntries; idx > newPosition; idx--)
            list[idx] = list[idx-1];
    }
    private boolean isFull() {
        return (numberOfEntries == capacity);
    }

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 0 && givenPosition < numberOfEntries);
        for (int index = givenPosition; index < numberOfEntries; index++){
            list[index] = list[index+1];
        }
    }

        /**
         *
         * add anEntry value to the top of the list
         *
         * @param anEntry
         */
    @Override
    public void add(T anEntry) {
        if (anEntry == null)
            throw new IllegalArgumentException();
        list[numberOfEntries] = anEntry;
        numberOfEntries ++;
        ensureCapacity();

    }

    /**
     * add anEntry value at the index idx and push the rest up
     *
     * @param anEntry
     * @param idx
     */
    @Override
    public void add(T anEntry, int idx) {
        if (idx < 0 || idx > numberOfEntries) throw new IndexOutOfBoundsException();
        makeRoom (idx);
        list[idx] = anEntry;
        numberOfEntries ++;
        ensureCapacity();
    }



    /**
     * remove the value at given index idx
     * and return the remove value
     *
     * @param idx
     * @return T
     */
    @Override
    public T remove(int idx) {
        T theEntry;
        if (idx < 0 || idx >= numberOfEntries) throw new IndexOutOfBoundsException("Remove fail");
        theEntry = list[idx];
        removeGap(idx);
        numberOfEntries--;
        return theEntry;
    }

    /**
     * return true if the given anEntry is remove from the list
     *
     * @param anEntry
     * @return T
     */
    @Override
    public boolean remove(T anEntry) {
        for(int i = 0; i < numberOfEntries; i++){
            T x = list[i];
            if(x == anEntry){
                list[i] = null;
                removeGap(i);
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    /**
     * clea the list by removing all entries
     */
    @Override
    public void clear() {
        if(numberOfEntries == 0) throw new EmptyStackException();
        for(T x: list){
            remove(x);
        }
        numberOfEntries = 0;

    }

    /**
     * replace the value at index idx with the given anEntry
     * return the value that was replace
     *
     * @param anEntry
     * @param idx
     * @return T
     */
    @Override
    public T replace( int idx, T anEntry) {
        if(idx > numberOfEntries) throw new IndexOutOfBoundsException("Replace Fail");
        T x = list[idx];
        list[idx] = anEntry;
        return x;
    }

    /**
     * get the entry from the idx given
     *
     * @param idx
     * @return T
     */
    @Override
    public T getEntry(int idx) {
        if(idx > numberOfEntries) throw new IndexOutOfBoundsException("Get Entry fail");
        return list[idx];
    }

    /**
     * return a new array contain the enities in list in the same
     * order
     *
     * @return Object[]
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[numberOfEntries];
        for (int idx = 0; idx < numberOfEntries; idx ++)
            result[idx] = list[idx];
        return result;
    }

    /**
     * does anEntry exist in the list
     *
     * @param anEntry
     * @return boolean
     */
    @Override
    public boolean contains(T anEntry) {
        for(T x : list){
            if(x == anEntry) return true;
        }
        return false;
    }

    /**
     * get the length of the List
     *
     * @return int
     */
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * check if empty
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

}
