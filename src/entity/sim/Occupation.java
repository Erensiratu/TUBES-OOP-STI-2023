package entity.sim;

import java.util.Random;

import entity.sim.profession.*;
import entity.sim.profession.Profession;

public class Occupation {
    Profession currentOccupation;
    int timesWorked;

    public Occupation(){
        setOccupation(getRandomProfession());
        timesWorked = 0;
    }

    public Profession getOccupation() {
        return currentOccupation;
    }

    public void setOccupation(Profession newOccupation){
        currentOccupation = newOccupation;
    }

    public Profession getRandomProfession(){
        int x = (new Random().nextInt(5) + 1);
        switch(x) {
            case 1:
                return new Clown();
            case 2:
                return new Cook();
            case 3:
                return new Police();
            case 4:
                return new Programmer();
            default:
                return new Doctor();
        }
    }

    public void addTimesWorked(int duration){
        timesWorked += duration;
    }

    public void work(Sim sim, int duration){
        Thread workThread = new Thread(() -> {
            sim.getAction().setIdle(false);
            System.out.println("Worker thread started");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sim.getAction().setIdle(true);
            System.out.println("Worker thread finished");
        });
        workThread.start();
    }
}
