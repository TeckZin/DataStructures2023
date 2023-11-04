package Queue;

public class Register {

    private double serviceTime;

    private double timeLost = 0.0;

    private int registerNum;
    private int queueLength = 0;
    private LinkQueue<Customer> queue;
    public Register(int registerNum, double serviceTime){
        queue = new LinkQueue<>();
        this.registerNum = registerNum;
        this.serviceTime = serviceTime;

    }

    public void addCustomer(){
        Customer customer = new Customer(serviceTime);
        queue.enqueue(customer);
        queueLength++;

    }

    public void dequeueCustomer(){
        queue.dequeue();
        queueLength--;
    }

    public int getQueueLength(){
        return queueLength;
    }

    public int updateCustomer(){




        if(!queue.isEmpty()){
            Customer c = queue.getFront();
            c.setRemainingTime(c.getRemainingTime() - (1.0/60));
            timeLost += (1.0/60);
            System.out.printf("Customer remaining time: %.2f\n", c.getRemainingTime());
            System.out.println(getQueueLength());
            if(c.getRemainingTime() <= 0){
                System.out.println("here");
                dequeueCustomer();
                if(queue.isEmpty()){
                    return 1;
                }
                c = queue.getFront();
                c.setRemainingTime(c.getRemainingTime() - timeLost );
                timeLost = 0;


                return 1;
            }
        }




       return 0;
    }
}
