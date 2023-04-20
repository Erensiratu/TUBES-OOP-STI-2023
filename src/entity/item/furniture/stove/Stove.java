package entity.item.furniture.stove;

import java.util.Scanner;

import entity.item.food.cuisine.*;
import entity.item.furniture.Furniture;
import entity.sim.Sim;

public abstract class Stove extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Stove(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void use(Sim sim){
        System.out.println("Pilih nomor masakan yang ingin dibuat:");
        String[] masakan = {"Bistik", "Nasi Ayam", "Nasi Kari", "Susu Kacang", "Tumis Sayur"};
        for (int i = 0; i < masakan.length; i++){
            System.out.println((i+1) + ". " + masakan[i]);
        }

        Cuisine temp;

        System.out.println("\nMasukkan nomor: ");
        int x = scanner.nextInt();
        
        while (!((x >= 1) && (x <= 5))){
            System.out.println("\nMasukan tidak valid\nMasukkan nomor: ");
            x = scanner.nextInt();
        }

        if (x == 1){
            temp = new Bistik(1);
        } else if (x == 2){
            temp = new NasiAyam(1); 
        } else if (x == 3){
            temp = new NasiKari(1); 
        } else if (x == 4){
            temp = new SusuKacang(1); 
        } else{
            temp = new TumisSayur(1); 
        }
        
        if(!sim.getInventory().checkContains(temp.getRecipe())){
            System.out.println("Bahan makanan tidak cukup untuk membuat Bisitk");
            return;
        } 
        Thread cookThread = new Thread(() -> {
            sim.getAction().setIdle(false);
            System.out.println(sim.getName() + " sedang memasak " + temp.getName());
            try {
                Thread.sleep((int) temp.getCookingTime()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sim.getAction().setIdle(true);
            System.out.println(sim.getName() + " selesai memasak " + temp.getName());
            sim.getStatus().addMood(10);
            sim.getInventory().addItem(temp);
        });
        cookThread.start();
    }

        // this.cook(cuisine);

    @Override
    public String getDescription(){
        return "Buat masak";
    }
}
