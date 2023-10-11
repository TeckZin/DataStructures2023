package Bag;

public class ArrayTesting {

    public ArrayTesting(){
        String[] testString = {"value1", "value2", "value3"};



        ArrayExcerise<String> arrayExcerise = new ArrayExcerise<>();

        String[] list = arrayExcerise.replace(testString, 1,"value4");


        arrayExcerise.Display(list);


    }

}
