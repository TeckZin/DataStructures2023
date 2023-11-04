package Queue;

public class Customer {

    private double remainingTime;

    public Customer(double serviceTime){
        remainingTime = serviceTime;

    }

    public double getRemainingTime(){
        return remainingTime;

    }

    public void setRemainingTime(double remainingTime){
        this.remainingTime = remainingTime;
    }

}
