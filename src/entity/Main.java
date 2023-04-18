package entity;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        Worker worker3 = new Worker();

        timer.addWorker(worker1);
        timer.start();
        

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        timer.addWorker(worker2);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        timer.addWorker(worker3);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }
}