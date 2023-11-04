package List;

public class Name implements Comparable<Name> {



    private String first;


    private String last;
    private SortedAList<String> gifts;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
        gifts = new SortedAList<String>();

    }

    public void addGifts(String gift) {
        gifts.addEntry(gift);
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
    public String getFullName(){
        return first + " " + last;

    }

    public String getFullGiftsList(){
        StringBuilder sum = new StringBuilder();
        for(int i = 0; i < gifts.getLength(); i++){
            String gift = gifts.getEntry(i);
            sum.append(gift).append(",");


        }
        return sum.toString();

    }
    public boolean compareFullName(Name name, Name name2){
        return name.getFullName().equals(name2.getFullName());

    }

    @Override
    public int compareTo(Name o) {
        int lastCmp = last.compareTo(o.last);
        return (lastCmp != 0 ? lastCmp : first.compareTo(o.first));
    }
}
