package Queue;

public class Store {

    double countDown = 6.0;

    final int NUM_REGS = 8;
    final double SERVICE_TIME = 2.0;


    private double arriveTime;
    private double arriveCountDownTime;

    private int lost = 0;
    private int served = 0;

    private Register[] registers = new Register[NUM_REGS];



    public Store(double arriveTime){
        this.arriveTime = arriveTime;
        this.arriveCountDownTime = arriveTime;
        if(this.arriveTime > SERVICE_TIME){
            int amountServed = (int) Math.floor(countDown/this.arriveTime);
            System.out.println(amountServed);
            System.exit(0);
        }
        for(int i = 0; i <  NUM_REGS; i++){
            Register register = new Register(i, SERVICE_TIME);
            registers[i] = register;
        }
        Register r = registers[0];
        r.addCustomer();

    }

    public void update(){
        countDown -=  (1.0/60);
        arriveCountDownTime -= (1.0/60);

        System.out.printf("CountDown: %.2f, ArriveTime: %.2f\n", countDown, arriveCountDownTime);
        if(countDown <= (1.0/60)){
            updateStats();
            System.out.println(served);
            System.out.println(lost);
            System.exit(0);
        }

       if(arriveCountDownTime <= 0){
           for(Register r : registers){
               if(r.getQueueLength() < 4){
                   arriveCountDownTime = arriveTime;
                   r.addCustomer();

                   updateStats();

                   return;

               }
           }
           arriveCountDownTime = arriveTime;
           lost++;
       }
       updateStats();

    }

    public void updateStats(){
        for(Register r : registers){
            int num = r.updateCustomer();
            served += num;
            if(num == 1 && countDown <= 0){
                System.out.println(served);
                System.out.println(lost);
                System.exit(0);
            }


        }


    }


}
