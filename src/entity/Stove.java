package entity;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class Stove extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Stove(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void use(Sim sim){
        if (!getVacancy()){
            System.out.println("\n\nBenda sedang digunakan oleh sim lain");
            return;
        }

        System.out.println("Daftar masakan:\n1. Bistik\n2. Nasi Ayam\n3. Nasi Kari\n4. Susu Kacang\n5. Tumis Sayur");

        Cuisine cuisine = null;
        
        while (cuisine == null){
            System.out.printf("\nNama masakan: ");
            String input = scanner.nextLine();
            cuisine = CuisineFactory.createCuisine(input, 1);
            if (cuisine == null){
                System.out.println("\n\nNama masaka tidak valid");
            }
        }
    
        ArrayList<Item> ingredients = new ArrayList<>();
        ingredients.addAll(cuisine.getRecipe());

        if(!sim.getInventory().getList().containsAll(ingredients)){
            System.out.println("Bahan makanan di inventory tidak cukup untuk membuat " + cuisine.getName());
            return;
        } 

        final Cuisine finalCuisine = cuisine;
        Thread cookThread = new Thread(() -> {
            setVacancy(false);
            
            sim.getAction().setIdle(false);

            System.out.println(sim.getName() + " sedang memasak " + finalCuisine.getName());

            try {
                Thread.sleep((int) finalCuisine.getCookingTime()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            sim.getAction().setIdle(true);
            System.out.println(sim.getName() + " selesai memasak " + finalCuisine.getName());
            sim.getStatus().addMood(10);
            sim.getInventory().addItem(finalCuisine);
            sim.getInventory().getList().removeAll(ingredients);

            setVacancy(true);
        });
        cookThread.start();
    }

    @Override
    public String getDescription(){
        return "Buat masak";
    }
}
