package entity.item.furniture;

import java.util.Random;

import entity.sim.Sim;

public abstract class Canvas extends Furniture{
    public Canvas(int quantity){
        super(quantity, 2, 1, 200);
    }

    public void use(Sim sim){
        Thread paintThread = new Thread(() -> {
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
        });

        // Memulai thread
        paintThread.start();

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