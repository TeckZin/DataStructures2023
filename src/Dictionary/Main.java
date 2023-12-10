package Dictionary;

import utils.Entry;

public class Main<K, V> {
    public static void main(String[] args) {
        TDProject tdProject = new TDProject();
        tdProject.readFile("C:\\Users\\Teck\\IntelliJ Files\\DataStructures2023\\DataStructures2023\\src\\Dictionary\\restaurants.txt");
//        System.out.println(tdProject.getPhoneNumber("Bistro 5,"));
        tdProject.print();





    }


}
