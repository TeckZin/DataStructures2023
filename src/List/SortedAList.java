package List;

import java.util.EmptyStackException;

//https://wentworth.brightspace.com/d2l/le/content/40338/viewContent/473830/View
public class SortedAList <T extends Comparable <? super T>> extends AList<T> implements SortedListInterface <T> {

    public SortedAList() {
        super();
    }
    @Override
    public void add (T newEntry) {
        throw new UnsupportedOperationException();

    }
    @Override
    public void add (T newEntry, int givenPosition) {
        throw new UnsupportedOperationException();
    }
    @Override
    public T replace (int givenPosition, T newEntry) {
        throw new UnsupportedOperationException();
    }

    public int getPosition( T anEntry){
        int position = 0;
        int compValue = 0;
        int length = getLength();
        while (position < length ) {
            compValue = anEntry.compareTo (getEntry(position));
            if (compValue <= 0)
                break;
            position ++;
        }
        if (position == length || compValue < 0)
            position = -1 - position;
        return position;
    }

    @Override
    public void clear() {
        if(getLength() == 0) throw new EmptyStackException();
        super.clear();

    }


    public void addEntry(T newEntry) {
        int newPosition = getPosition (newEntry);
        if (newPosition < 0)
            newPosition = -newPosition - 1;
        super.add(newEntry, newPosition);
    }
    public boolean removeEntry(T anEntry) {
        int position = getPosition (anEntry);
        if (position >= 0){
            remove (position);
            return true;
        }
        return false;
    }




}
