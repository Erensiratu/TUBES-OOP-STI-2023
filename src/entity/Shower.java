package entity;


public class Shower extends Furniture{
    public Shower(int quantity){
        super(quantity, 2, 1, 100);
    }

    public static Shower getInstance(int quantity){
        return new Shower(quantity);
    }

    public void use(Sim sim){
        if (!getVacancy()){
            System.out.println("\n\nBenda sedang digunakan oleh sim lain");
            return;
        }
        
        Thread showerThread = new Thread(() -> {
            setVacancy(false);
            
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

            setVacancy(true);
        });

        // Memulai thread
        showerThread.start();
        try {
            showerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
