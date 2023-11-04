package Queue;

public class Main <T>  {

    public static void main(String[] args) {
        Store store = new Store(1.0);

        while(true){
            store.update();
        }
    }

}

