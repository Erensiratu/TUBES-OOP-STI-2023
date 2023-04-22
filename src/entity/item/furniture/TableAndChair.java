package entity.item.furniture;

import java.util.Scanner;

import entity.item.Item;
import entity.item.food.Food;
import entity.sim.Sim;

public class TableAndChair extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public TableAndChair(int quantity){
        super(quantity, 3, 3, 50);
    }

    public void use(Sim sim){
        System.out.print("Daftar makanan yang ada di inventory " + sim.getName());
    
        int count = 1;
        for (Item item : sim.getInventory().getList()){
            if (item instanceof Food){
                Food food = (Food) item;
                System.out.println(count + ". " + food.getName() + " (" + food.getFullness() + " Kekenyangan) : " + food.getQuantity() + " buah");
            }
        }
        
        System.out.println("\nMasukkan nama makanan: ");
        String foodName = scanner.nextLine();
        boolean found = false;
        Food currentFood = null;
    
        for (Item item : sim.getInventory().getList()){
            if (item.getName().equals(foodName)){
                found = true;
                currentFood = (Food) item;
                break;
            }
        }
    
        if (!found){
            System.out.println("Tidak ada makanan dengan nama " + foodName);
            return;
        }
    
        final int fullness = currentFood.getFullness();
     
        Thread eatThread = new Thread(() -> {
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
        });
    
        // Memulai thread
        eatThread.start();
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
