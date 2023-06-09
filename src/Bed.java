

import java.util.Scanner;


public abstract class Bed extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    @Override
    public void use(Sim sim) {
        if (!getVacancy()){
            System.out.println("\n\nBenda sedang digunakan oleh sim lain");
            return;
        }
        // Meminta waktu tidur (dalam detik)
        int input = -1;
        while (input < 180) {
            System.out.printf("Masukkan waktu tidur untuk sim\nWaktu tidur (dalam detik): ");
            input = scanner.nextInt();

            if (input < 180){
                System.out.println("\nWaktu tidak boleh kurang dari 180 detik");
            }
        }
    
        // Implementasi sudah tidur harian
        // WIP

        // Thread tidur
        final int sleepTime = input;
        Thread sleepThread = new Thread(() -> {
            sim.getAction().setIdle(false);
    
            System.out.println("\n" + sim.getName() + " sedang tidur selama " + sleepTime + " detik");
    
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Menambahkan efek tidur pada status sim
            // Efek pada status baru ada setelah sim tidur selama 4 menit (240 detik)
            int fullSleep = sleepTime / 240;
            sim.getStatus().addMood(fullSleep * 30);
            sim.getStatus().addHealth(fullSleep * 20);
    
            sim.getAction().setIdle(true);
    
            System.out.println("\n" + sim.getName() + " telah bangun");
        });

        // Memulai thread
        sleepThread.start();
        try {
            sleepThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
