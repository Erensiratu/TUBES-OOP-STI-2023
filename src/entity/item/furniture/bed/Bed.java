package entity.item.furniture.bed;

import java.util.Scanner;

import entity.item.furniture.Furniture;
import entity.sim.Sim;

public abstract class Bed extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void sleep(Sim sim){
        // Meminta waktu tidur (dalam detik)
        final int duration = scanner.nextInt();

        // Mengecek apakah waktu tidur valid
        if(duration < 180){
            System.out.println("Waktu tidur sim setidaknya 3 menit (180 detik)");
        } else {
            // Implementasi sudah tidur harian
            // WIP
            // Thread tidur
            Thread sleepThread = new Thread(() -> {
                sim.getAction().setIdle(false);

                System.out.println(sim.getName() + " sedang tidur selama " + duration + " detik");
                
                try {
                    Thread.sleep(duration * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Menambahkan efek tidur pada status sim
                // Efek pada status baru ada setelah sim tidur selama 4 menit (240 detik)
                int fullSleep = duration / 240;
                sim.getStatus().addMood(fullSleep * 30);
                sim.getStatus().addHealth(fullSleep * 20);

                sim.getAction().setIdle(true);

                System.out.println(sim.getName() + " telah bangun");
            });

            // Memulai thread
            sleepThread.start();
        }
    }

    @Override
    public void use(Sim sim){
        sleep(sim);
    }

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
