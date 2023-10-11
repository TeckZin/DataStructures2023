package Bag;

public class ArrayExcerise <T> {


    ArrayExcerise(){
        System.out.println("Bag.ArrayExcerise Run");

    }
    public T[] replace (T[] list, int index, T value){

        list[index] = value;
        return list;
    }

    public boolean replace (T[] list, T oldValue, T newValue){
        for(int i = 0; i <= list.length -1; i++){
            if(oldValue == list[i]){
                list[i] = newValue;
                return true;
            }
        }
        return false;
    }
    public void Display(T[] list){
        for (int i = 0; i <= list.length -1; i++){
            System.out.println(list[i]);
        }
    }




}
