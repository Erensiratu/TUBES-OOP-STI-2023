package entity;

import java.util.Random;
import java.util.Scanner;


public class Occupation implements ChangeDayListener {
    Sim sim;
    Profession profession;
    int timesWorked;
    boolean excessTime;
    int daySinceChangeJob;

    Scanner scanner = new Scanner(System.in);

    public Occupation(Sim sim){
        this.sim = sim;
        profession = getRandomProfession();
        timesWorked = 0;
        daySinceChangeJob = 999;
        Sim.getCurrentWorld().getClock().addEventListener(this);
    }

    public Profession getProfession() {
        return profession;
    }

    public void changeProfession(){
        System.out.println("1.Badut Sulap\n2. Koki\n3. Dokter\n4. Polisi\5. Programmer");
        int input = 0;
        while (input < 1 || input > 5){
            System.out.print("\nMasukkan angka 1-5: ");
            input = scanner.nextInt();
            if (input < 1 || input > 5){
                System.out.println("\n\nMasukkan angka yang valid");
            }
        }

        Profession newProfession = null;

        switch(input) {
            case 1:
                newProfession = Clown.getInstance();
                break;
            case 2:
                newProfession = Cook.getInstance();
                break;
            case 3:
                newProfession = Doctor.getInstance();
                break;
            case 4:
                newProfession = Police.getInstance();
                break;
            case 5:
                newProfession = Programmer.getInstance();
                break;
        }

        if ((timesWorked > 720) && (sim.getStatus().getMoney() < newProfession.getSalary()/2)){
            sim.getStatus().decreaseMoney(newProfession.getSalary()/2);
            profession = newProfession;           
        } else{
            System.out.println(sim.getName() + " belum dapat mengganti pekerjaannya saat ini");
        }
    }

    public Profession getRandomProfession(){
        int x = (new Random().nextInt(5) + 1);
        switch(x) {
            case 1:
                return Clown.getInstance();
            case 2:
                return Cook.getInstance();
            case 3:
                return Doctor.getInstance();
            case 4:
                return Police.getInstance();
            default:
                return Programmer.getInstance();
        }
    }

    public void addTimesWorked(int duration){
        timesWorked += duration;
    }

    public void doWork(){
        if (daySinceChangeJob <= 2){
            System.out.println("Maaf, belum bisa bekerja dengan pekerjaan barunya, silahkan coba lagi di lain hari");
            return;
        }

        int input = -1;

        while ((input % 120 != 0) || (input <= 0)){
            System.out.print("\nMasukkan durasi kerja dalam detik\nDurasi kerja: ");
            input = scanner.nextInt();

            if ((input % 120 != 0) || (input <= 0)){
                System.out.println("\n\nDurasi kerja harus dalam kelipatan 120 dan lebih dari 0");
            }
        }

        final int duration = input;
        
        Thread workThread = new Thread(() -> {
            sim.getAction().setIdle(false);
            System.out.println("\n" + sim.getName() + " mulai bekerja selama " + duration + " detik");
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

            sim.getStatus().decreaseHunger(duration/30*10);
            sim.getStatus().decreaseMood(duration/30*10);
            sim.getStatus().addMoney(profession.getSalary()*((totalDuration)/240));
            
            sim.getAction().setIdle(true);
            
            System.out.println(sim.getName() + "selesai bekerja");
        });

        workThread.start();
        try {
            workThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void changeDayUpdate(){
        decrementDayCounter();
    }
    public synchronized void decrementDayCounter(){
        daySinceChangeJob--;
    }
}
