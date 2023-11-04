package List;

public class Test {
    public static void main(String[] args) {
        SortedLList<String> sortedLList = new SortedLList<>();

        sortedLList.addEntry("1");
        sortedLList.addEntry("3");
        sortedLList.addEntry("6");
        int x = sortedLList.getPosition("hellofefefefefe");
        System.out.println(x);

        SortedAList<String> alist = new SortedAList<>();
        System.out.println(alist.getPosition("String"));
    }
}
