package List;

public interface NiceInterface <Name extends Comparable<? super Name>>{


    public int getPositionStringFirst(String first, String last);

    public int getPositionStringLast(String last);
    public void addEntry(Name name);

    public boolean removeEntry(Name name);
    public void clear();
    public boolean compareName(Name name1, Name name2);
    public boolean containsNameBoolean(Name name);



}
