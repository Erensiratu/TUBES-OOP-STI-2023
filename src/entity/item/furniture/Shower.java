package entity.item.furniture;

import entity.sim.Sim;

public abstract class Shower extends Furniture{
    public Shower(int quantity){
        super(quantity, 2, 1, 100);
    }

    public void use(Sim sim){
        Thread showerThread = new Thread(() -> {
            sim.getAction().setIdle(false);

            System.out.println(sim.getName() + " sedang mandi");
                
            try {
                Thread.sleep(120 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Menambahkan efek mandi pada status sim
            sim.getStatus().addMood(20);
            sim.getStatus().addHealth(10);

            sim.getAction().setIdle(true);

            System.out.println(sim.getName() + " telah selesai mandi");
        });

        // Memulai thread
        showerThread.start();

    }

    @Override
    public String getDescription(){
        return "Buat mandi";
    }

    @Override
    public String getName(){
        return "Shower";
    }
}
