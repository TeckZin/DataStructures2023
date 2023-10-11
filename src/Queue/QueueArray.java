package Queue;

public class QueueArray <T> implements QueueInterface <T> {

    private T[] queueArr;
    private int capacity;

    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 10;

    public QueueArray (int capacity){
        queueArr = (T[]) new Object [capacity];
        this.capacity = capacity;
        frontIndex = 0;
        backIndex = capacity - 1;

    }

    public QueueArray(){
        this(DEFAULT_CAPACITY);


    }

    @Override
    public void enqueue(T anEntry) {
        backIndex = (backIndex + 1) % capacity;
        queueArr[backIndex]= anEntry;

        if (isFull()){
            ensureCapacity();
        }


    }

    @Override
    public T dequeue() {
        if (isEmpty ())
            throw new EmptyQueueException ("Empty queue: cannot dequeue");
        T front = getFront();
        queueArr[frontIndex] = null;
        frontIndex = (frontIndex +1) % capacity;
        if (isEmpty () ) {
            frontIndex = 0;
            if (capacity > DEFAULT_CAPACITY ) {
                capacity = DEFAULT_CAPACITY;
            }
            queueArr = (T[]) new Object[capacity];
            backIndex = capacity - 1;
        }
        return front;
    }

    @Override
    public T getFront() {
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        return queueArr [ frontIndex] ;
    }

    @Override
    public boolean isEmpty() {
        return (frontIndex == (backIndex + 1) % capacity);
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            dequeue();
        }
    }

    private boolean isFull(){
        return (frontIndex == (backIndex + 2) % capacity);

    }

    private void ensureCapacity(){
        T[] oddQueue = queueArr;
        T[] tempArr = (T[]) new Object[2 * capacity];
        for(int i = 0; i < capacity; i++){
            tempArr[i] = oddQueue[(frontIndex + i) % capacity];
        }
        frontIndex = 0;
        backIndex = capacity - 2;
        capacity *= 2;
        queueArr = tempArr;

    }
}
