package List;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.EmptyStackException;

public class SantaClaus {
    private  Nice<Name> niceList;
    private LList<Name> naughtyList;

    private AList<Name> unSortedNiceList;

    private SortedLList<Name> allPeople;
    public SantaClaus(){
        niceList = new Nice<Name>();
        unSortedNiceList = new AList<Name>();
        naughtyList = new LList<Name>();
        allPeople = new SortedLList<Name>();
    }

    public boolean addNice(Name name){

        if( niceList.contains(name))return false;
        niceList.addEntry(name);
        unSortedNiceList.add(name);
        allPeople.addEntry(name);
        return true;
    }

    public void addNaughty(Name name){
        naughtyList.add(name);
        allPeople.addEntry(name);
    }

    public Name getNameNice (int position){

        return niceList.getEntry(position);
    }

    public Name getNameNiceUnSorted(int position){
        return unSortedNiceList.getEntry(position);
    }

    public Name getNameNaughty(int position){
        return naughtyList.getEntry(position);
    }

    public void printUnSortedNice(){
        for(int i = 0; i < unSortedNiceList.getLength(); i++){
            Name name = unSortedNiceList.getEntry(i);
            System.out.println(name.getFirst());
        }


    }

    public void printSortedNice(){
        for(int i = 0; i < niceList.getLength(); i++){
            Name name = niceList.getEntry(i);
            System.out.println(name.getFirst());
        }

    }

    public void changeNiceToNaughty(Name name){
//        System.out.println(name.getFullName());
        if(niceList.isEmpty()) throw new EmptyStackException();
        if(niceList.containsNameBoolean(name)){
            Name name2 = niceList.containsName(name);
            niceList.removeEntry(name2);
            unSortedNiceList.remove(name2);
            naughtyList.add(name2);

        }



    }

    public void changeNaughtyToNice(Name name){
        if(naughtyList.isEmpty())throw new EmptyStackException();
        Name name1 = enterGifts(name);
        if(niceList.contains(name1) || niceList.containsNameBoolean(name1)){
            System.out.println("already exist");
            return;
        } else{
            niceList.add(name1);
            unSortedNiceList.add(name1);
            naughtyList.remove(name1);
        }




    }

    private Name enterGifts(Name name){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter wanted gift -- Enter Break to Break: ");

            String gift = sc.nextLine();


            if(gift.equals("Break")){
                break;
            }
            name.addGifts(gift);


        }
        return name;

    }

    public void createFile(){
        try{
            File newFile = new File("C:\\Users\\Teck\\IntelliJ Files\\DataStructures2023\\DataStructures2023\\src\\List\\newPeople.txt");
            String path = newFile.getAbsolutePath();
            if(newFile.createNewFile()){
                System.out.println("File Created");



            }else{
                System.out.println("File Already Created");


            }
            printToFile(path);
        }catch (IOException e){
            System.out.println(e);
        }


    }

    private void printToFile(String path){
        try{
            PrintStream writer = new PrintStream(path);
            writer.println("Nice List");
            for(int i = 0; i < niceList.getLength(); i++){
                Name name = niceList.getEntry(i);
                writer.print(name.getFullName());
                writer.println(name.getFullGiftsList());

            }

            writer.println("Naughty");
            for(int i = 0; i < naughtyList.getLength(); i++){
                Name name = naughtyList.getEntry(i);
                if(name != null) writer.println(name.getFullName());
            }
            writer.close();


        } catch (IOException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }


    }





}
