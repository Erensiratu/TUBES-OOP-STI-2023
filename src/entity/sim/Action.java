package entity.sim;

import java.util.Scanner;

import entity.item.Item;
import entity.item.Purchaseable;
import entity.item.Useable;
import entity.item.food.ingredient.IngredientFactory;
import entity.item.furniture.FurnitureFactory;

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
        System.out.println("Sim akan mulai melakukan aktivitas.");
    }

    public void exercise(int duration) {
        System.out.println("Sim berolahraga selama " + duration/60 + " menit.");
    }

    public void giftItem(Item gift, Sim receiver) {
        System.out.println("Sim memberikan " + gift.getName() + " kepada " + receiver.getName() + ".");
        receiver.getInventory().addItem(gift);
    }

    public void transferMoney(int amount, Sim receiver) {
        System.out.println("Sim men-transfer $" + amount + " to " + receiver.getName() + ".");
        receiver.getStatus().addMoney(amount);
    }

    public void dayDream(int duration) {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        idle = false;
        System.out.println("Berkhayal selama " + duration/60 + " menit...");

        // Simulate daydreaming for the given duration
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Selesai berkhayal!");
    }

    public void converse(Sim otherSim) {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        idle = false;
        System.out.println("Berkomunikasi dengan " + otherSim + "...");

        // Simulate a conversation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Selesai berkomunikasi!");
    }

    public void work(int duration) {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }
        sim.getOcupation().doWork();
    }

    public void useItem(int duration) {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        Useable item = (Useable) sim.getItem();

        idle = false;
        System.out.println("Menggunakan " + item + "...");

        // Use the item
        item.use(sim);

        idle = true;
        System.out.println("Selesai menggunakan!");
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