package Queue;

public interface QueueInterface <T> {

    public void enqueue(T anEntry);
    public T dequeue();
    public T getFront();
    public boolean isEmpty();
    public void clear();


}
