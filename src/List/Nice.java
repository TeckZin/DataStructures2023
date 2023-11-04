package List;

import java.util.EmptyStackException;

public class Nice< T extends Comparable <? super Name>> extends AList<Name> implements NiceInterface <Name> {

    public Nice() {
        super();

    }

    @Override
    public void add(Name newEntry) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(Name newEntry, int givenPosition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name replace(int givenPosition, Name newEntry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPositionStringFirst(String first, String last) {
        int position = 0;
        int compValue = 0;
        int length = getLength();
        while (position < length) {
            Name name = getEntry(position);
            compValue = first.compareTo(name.getFirst());
            if (compValue < 0)
                break;
            else if(compValue == 0){
                return getPositionStringLast(last);
            }
            position++;
        }
        if (position == length || compValue < 0)
            position = -1 - position;
        return position;
    }
    @Override
    public int getPositionStringLast(String last){
        int position = 0;
        int compValue = 0;
        int length = getLength();
        while (position < length) {
            Name name = getEntry(position);
            compValue = last.compareTo(name.getLast());
            if (compValue <= 0)
                break;
            position++;
        }
        if (position == length || compValue < 0)
            position = -1 - position;
        return position;

    }


    /**
     * Sorted my the first name
     *
     * @param name
     */
    @Override
    public void addEntry(Name name) {
        String first = name.getFirst();
        String last = name.getLast();
        int newPosition = getPositionStringFirst(first, last);
        if (newPosition < 0)
            newPosition = -newPosition - 1;
        super.add(name, newPosition);


    }

    @Override
    public boolean removeEntry(Name name) {
        int position = getPositionStringFirst(name.getFirst(), name.getLast());
        if (position >= 0) {
            remove(position);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        if (getLength() == 0) throw new EmptyStackException();
        super.clear();
    }

    @Override
    public boolean compareName(Name name1, Name name2) {
//        System.out.println("name1");
//        System.out.println(name1.getFullName());
//        System.out.println("name2");
//        System.out.println(name2.getFullName());
        return name1.getFullName().equals(name2.getFullName());
    }
    @Override
    public boolean containsNameBoolean(Name name){
        for(int i = 0; i < getLength(); i++){
            Name tempName = (Name)getEntry(i);
            if(compareName(name, tempName)) return true;
        }
        return false;
    }

    public Name containsName(Name name){
        for(int i = 0; i < getLength(); i++){
            Name tempName = (Name)getEntry(i);
            if(compareName(name, tempName)) return tempName;
        }
        return null;
    }



}