package List;


import java.io.File;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SantaClaus santaClaus = new SantaClaus();
        try{
            File file = new File("C:\\Users\\Teck\\IntelliJ Files\\DataStructures2023\\DataStructures2023\\src\\List\\people.txt");
            Scanner rd = new Scanner(file);
            while(rd.hasNext()) {
                String text = rd.nextLine();
//                System.out.println(text);
                String[] arrOfString = text.split(" ", 3);

                String first = null;
                String last = null;

                for (int i = 0; i < arrOfString.length; i++) {

//                    System.out.println(i);
//                    System.out.println(arrOfString.length);
                    String str = arrOfString[i];
//                    System.out.println(str);
                    if (!str.equals(" ")) {
                        if (i == 0) first = str;
                        else if (i == 1) last = str;
                        else {

                            if((str.toUpperCase()).compareTo(str) == 0){
                                Name name = new Name(first, last); //make sorted lst here

//                                System.out.println("add naughty");
//                                System.out.printf("%s,%s\n", first, last);
                                santaClaus.addNaughty(name);

//                                System.out.println("empty");
                            } else{
                                Name name = new Name(first, last);
                                String[] giftsArray = str.split(",");

//                                System.out.println("add nice");
//                                System.out.printf("%s,%s\n", first, last);
                                for(String x : giftsArray){
//                                    System.out.println(x);

                                    name.addGifts(x);
//                                    System.out.println("here");
                                }

                                santaClaus.addNice(name);
                            }

                        }
                    }


                }


            }
            addMore(santaClaus, sc);
//            santaClaus.printUnSortedNice();
            System.out.println("new");
//            santaClaus.printSortedNice();
            changeObject(sc, santaClaus);
            santaClaus.createFile();

            sc.close();








        } catch (Exception e){
            System.out.println("here");
            e.printStackTrace();

        }





    }

    public static void changeObject(Scanner sc, SantaClaus santaClaus){
        System.out.println("Would you like to change Objects");
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("Y")){
            System.out.println("Which List 0 for Nice to Naughty 1 for Naugty to Nice: ");
            int x = sc.nextInt(); // make recussion srike 3

            System.out.println("Enter first name of original list: ");
            String first = sc.next();



            System.out.println("Enter last name of original list: ");
            String last = sc.next();


            if(x == 0){

                Name name = new Name(first, last);
                System.out.println(name.getFullName());

                santaClaus.changeNiceToNaughty(name);
            } else{
                Name name = new Name(first, last);

                santaClaus.changeNaughtyToNice(name);
            }


        }


    }

    public static void addMore(SantaClaus santaClaus, Scanner sc){

        System.out.println("Would you like to add more names for nice list: ");
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("Y")){

            System.out.println("Enter First Name: ");
            String first = sc.nextLine();
            System.out.println("Enter Last Name: ");
            String second = sc.nextLine();
            Name name = new Name(first, second);

            while(true){
                System.out.println("Enter wanted gift -- Enter Break to Break: ");

                String gift = sc.nextLine();


                if(gift.equals("Break")){
                    break;
                }
                name.addGifts(gift);


            }
            if(!santaClaus.addNice(name)){
                System.out.println("Name already exist");
            }

            addMore(santaClaus, sc);
        }else{
            System.out.println("No more to add");
        }


    }
}
