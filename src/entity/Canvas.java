package entity;

import java.util.Random;



public class Canvas extends Furniture{
    public Canvas(int quantity){
        super(quantity, 2, 1, 200);
    }

    public static Canvas getInstance(int quantity){
        return new Canvas(quantity);
    }

    public void use(Sim sim){
        if (!getVacancy()){
            System.out.println("\n\nBenda sedang digunakan oleh sim lain");
            return;
        }
        
        Thread paintThread = new Thread(() -> {
            setVacancy(false);
            
            sim.getAction().setIdle(false);

            System.out.println(sim.getName() + " sedang melukis sebuah lukisan");
                
            try {
                Thread.sleep(180 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int revenue = new Random().nextInt(5) * 3;
            
            sim.getStatus().addMoney(revenue);
            sim.getStatus().decreaseHunger(10);
            sim.getStatus().addMood(10);

            sim.getAction().setIdle(true);

            System.out.println(sim.getName() + " telah selesai melukis dang mendapatkan uang sebanyak " + revenue);

            setVacancy(true);
        });

        // Memulai thread
        paintThread.start();
        try {
            paintThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getDescription(){
        return "Buat gambar";
    }

    @Override
    public String getName(){
        return "Kanvas Lukis";
    }
}