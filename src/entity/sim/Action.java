package entity.sim;
<<<<<<< HEAD
import java.util.ArrayList;

import javax.print.attribute.standard.QueuedJobCount;
=======
import java.util.Scanner;
>>>>>>> c83d4c0640615babb62bed276c8473b733dde965

import entity.item.Item;
import entity.item.Purchaseable;
import entity.item.Useable;
<<<<<<< HEAD
import entity.item.furniture.*;
import entity.item.furniture.bed.KingBed;
import entity.item.furniture.bed.QueenBed;
import entity.item.furniture.bed.SingleBed;
import entity.item.furniture.stove.EStove;
import entity.item.furniture.stove.GasStove;
=======
import entity.item.food.ingredient.IngredientFactory;
import entity.item.furniture.FurnitureFactory;
>>>>>>> c83d4c0640615babb62bed276c8473b733dde965

public class Action {
    private boolean idle;
    private Sim sim;
    Scanner scanner = new Scanner(System.in);

    public Action(Sim sim){
        idle = true;
        this.sim = sim;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public void startActivity() {
        System.out.println("Sim is starting an activity.");
    }

    public void exercise(int duration) {
        System.out.println("Sim is exercising for " + duration + " minutes.");
    }

    public void giftItem(Item gift, Sim receiver) {
        System.out.println("Sim is gifting " + gift.getName() + " to " + receiver.getName() + ".");
        receiver.getInventory().addItem(gift);
    }

    public void transferMoney(int amount, Sim receiver) {
        System.out.println("Sim is transferring $" + amount + " to " + receiver.getName() + ".");
        receiver.getStatus().addMoney(amount);
    }

    public void dayDream(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Daydreaming for " + duration + " hours...");

        // Simulate daydreaming for the given duration
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Done daydreaming!");
    }

    public void converse(Sim otherSim) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Conversing with " + otherSim + "...");

        // Simulate a conversation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Done conversing!");
    }

    public void work(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }
        sim.getOcupation().doWork();
    }

    public void useItem(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        Useable item = (Useable) sim.getItem();

        idle = false;
        System.out.println("Using item " + item + "...");

        // Use the item
        item.use(sim);

        idle = true;
        System.out.println("Done using item!");
    }

    public void buyFurniture(){
        Purchaseable item = null;
        int input = -1;
        int quantity = -1;
        String itemName = null;
        System.out.println("Silakan pilih kategori belanja:\n1 untuk Furnitur\n2 untuk Bahan Makanan\n3 untuk Batal\n");
        
        while ((input < 1) || (input > 3)){
            System.out.printf("Masukkan nomor: ");
            input = scanner.nextInt();
            System.out.println();
        }
    
        switch (input){
            case 1:
                System.out.println("Daftar Furnitur:\n1. Kasur Single\n2. Kasur Queen\n3. Kasur King\n4. Kompor Gas\n5. Kompor Listrik\n6. Kanvas\n7. Jam\n8. Shower\n9. Meja dan Kursi\n 10. Toilet\n");
                while (itemName == null){
                    System.out.printf("Masukkan nama furnitur: ");
                    itemName = scanner.nextLine();
                }
                item = FurnitureFactory.createFurniture(itemName, 0);
                break;
            case 2:
                System.out.println("Daftar Bahan Makanan:\n1. Ayam\n2. Bayam\n3. Kacang\n4. Kentang\n5. Nasi\n6. Sapi\n7. Susu\n8. Wortel\n");
                while (itemName == null){
                    System.out.printf("Masukkan nama bahan makanan: ");
                    itemName = scanner.nextLine();
                }
                item = IngredientFactory.createIngredient(itemName, 0);
                break;
            case 3:
                return;
            default:
                throw new IllegalArgumentException("Invalid option: " + input);
        }
    
        while (quantity <= 0){
            System.out.printf("Masukkan jumlah barang: ");
            quantity = scanner.nextInt();
            System.out.println();
        }
    
        final Item finalItem = (Item) item;
        finalItem.add(quantity);
        
        if (sim.getStatus().getMoney() >= item.getPrice() * quantity){
            sim.getStatus().decreaseMoney(item.getPrice() * quantity);
            final int deliveryTime = item.getDelliveryTime();
            Thread buyThread = new Thread(() -> {
    
                System.out.println("Barang sedang diantar ke " + sim.getName());
                    
                try {
                    Thread.sleep(deliveryTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                sim.getInventory().addItem(finalItem);
                System.out.println(sim.getName() + " telah menerima pesanannya");
            });
    
            // Memulai thread
            buyThread.start();
        } else{
            System.out.println("Uang milik " + sim.getName() + " tidak cukup untuk membeli barang ini");
        }
    }
}