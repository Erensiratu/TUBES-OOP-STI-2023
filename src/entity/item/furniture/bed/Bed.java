package entity.item.furniture.bed;

import java.util.Scanner;

import entity.item.furniture.Furniture;
import entity.sim.Sim;

public abstract class Bed extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    @Override
    public void use(Sim sim){
        // Meminta waktu tidur (dalam detik)
        System.out.println("Masukkan waktu tidur untuk sim:\n Waktu tidur: ");
        int x = scanner.nextInt();

        while (x < 180){
            System.out.println("\nWaktu tidur sim setidaknya 3 menit (180 detik)");
            System.out.println("Masukkan waktu tidur untuk sim:\n Waktu tidur: ");
            x = scanner.nextInt();
        }

        final int duration = x;

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

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
