package entity.item.furniture;

import entity.sim.Sim;

public class Toilet extends Furniture{
    public Toilet(int quantity){
        super(quantity, 1, 1, 50);
    }

    public static Toilet getInstance(int quantity){
        return new Toilet(quantity);
    }

    public void use(Sim sim){
        Thread toiletThread = new Thread(() -> {
            sim.getAction().setIdle(false);

            System.out.println(sim.getName() + " sedang menggunakan toilet");
                
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Menambahkan efek mandi pada status sim
            sim.getStatus().decreaseHunger(20);
            sim.getStatus().addMood(10);

            sim.getAction().setIdle(true);

            System.out.println(sim.getName() + " telah selesai menggunakan toilet");
        });

        // Memulai thread
        toiletThread.start();

    }

    @Override
    public String getDescription(){
        return "Buat buang air";
    }

    @Override
    public String getName(){
        return "Toilet";
    }
}
