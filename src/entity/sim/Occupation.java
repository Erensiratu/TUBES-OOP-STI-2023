package entity.sim;

import java.util.Random;
import java.util.Scanner;

import entity.sim.profession.*;
import entity.sim.profession.Profession;

public class Occupation {
    Sim sim;
    Profession currentOccupation;
    int timesWorked;
    boolean excessTime;

    Scanner scanner = new Scanner(System.in);

    public Occupation(Sim sim){
        this.sim = sim;
        setOccupation(getRandomProfession());
        timesWorked = 0;
    }

    public Profession getOccupation() {
        return currentOccupation;
    }

    public void setOccupation(Profession newOccupation){
        if ((timesWorked > 720) && (sim.getStatus().getMoney() < newOccupation.getSalary()/2)){
            sim.getStatus().decreaseMoney(newOccupation.getSalary()/2);
            currentOccupation = newOccupation;           
        } else{
            System.out.println(sim.getName() + " belum dapat mengganti pekerjaannya saat ini");
        }
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

    public void work(){
        System.out.println("Masukkan durasi kerja dalam detik\n Durasi kerja: ");
        int x = scanner.nextInt();

        while ((x % 120 != 0) || (x <= 0)){
            System.out.println("Durasi kerja harus dalam kelipatan 120\n Durasi kerja");
            x = scanner.nextInt();
        }

        final int duration = x;
        
        Thread workThread = new Thread(() -> {
            sim.getAction().setIdle(false);
            System.out.println(sim.getName() + " mulai bekerja selama " + duration + " detik");
            try {
                Thread.sleep((duration) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            addTimesWorked(duration);

            int totalDuration = duration;
            if (duration % 240 != 0){
                if (excessTime){
                    totalDuration += 120;
                    excessTime = false;
                } else{
                    excessTime = true;
                }
            }

            sim.getStatus().addMoney(currentOccupation.getSalary()*((totalDuration)/240));
            
            sim.getAction().setIdle(true);
            
            System.out.println(sim.getName() + "selesai bekerja");
        });

        workThread.start();
    }
}
