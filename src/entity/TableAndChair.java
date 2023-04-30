package entity;

import java.util.Scanner;


public class TableAndChair extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public TableAndChair(int quantity){
        super(quantity, 3, 3, 50);
    }

    public static TableAndChair getInstance(int quantity){
        return new TableAndChair(quantity);
    }

    public void use(Sim sim){
        if (!getVacancy()){
            System.out.println("\n\nBenda sedang digunakan oleh sim lain");
            return;
        }
        
        System.out.print("Daftar makanan yang ada di inventory " + sim.getName() + "\n");

        for (Item item : sim.getInventory().getList()){
            if (item instanceof Food){
                Food food = (Food) item;
                System.out.println("> " + food.getName() + " (" + food.getFullness() + " Kekenyangan) : " + food.getQuantity() + " buah");
            }
        }
        
        System.out.println("\nMasukkan nama makanan: ");
        String foodName = scanner.nextLine();
        boolean found = false;
        Food currentFood = null;
    
        for (Item item : sim.getInventory().getList()){
            if (item.getName().toLowerCase().equals(foodName.toLowerCase())){
                found = true;
                currentFood = (Food) item;
                sim.getInventory().removeItem(item, 1);
                break;
            }
        }
    
        if (!found){
            System.out.println("Tidak ada makanan dengan nama " + foodName);
            return;
        }
    
        final int fullness = currentFood.getFullness();
     
        Thread eatThread = new Thread(() -> {
            setVacancy(false);
            
            sim.getAction().setIdle(false);
    
            System.out.println(sim.getName() + " sedang makan " + foodName);
                
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Menambahkan efek mandi pada status sim
            sim.getStatus().addHunger(fullness);
    
            sim.getAction().setIdle(true);
    
            System.out.println(sim.getName() + " telah selesai makan");

            setVacancy(true);
        });
    
        // Memulai thread
        eatThread.start();
        try {
            eatThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription(){
        return "Buat makan";
    }

    @Override
    public String getName(){
        return "Meja Dan Kursi";
    }
}
