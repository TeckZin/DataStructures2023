package List;
// https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/472846/View
public interface SortedListInterface <T extends Comparable<? super T>>{


    public void addEntry(T newEntry);

    public boolean removeEntry(T anEntry);

    public int getPosition(T anEntry);
    public T getEntry(int givenPosition);
    public boolean contains (T anEntry);
    public T remove (int givenPosition);
    public void clear();
    public int getLength();
    public boolean isEmpty();
    public Object [] toArray();




}
