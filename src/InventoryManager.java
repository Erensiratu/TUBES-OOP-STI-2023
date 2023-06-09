

import java.util.ArrayList;
import java.util.Scanner;



public class InventoryManager {
    ArrayList<Item> inventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Sim sim;

    public InventoryManager(Sim sim){
        this.sim = sim;
    }

    public ArrayList<Item> getList() {
        return inventory;
    }

    public void addItem(Item newItem){
        boolean exists = false;
        for (Item item : inventory) {
            if (item.getName().equals(newItem.getName())) {
                item.add(newItem.getQuantity());
                exists = true;
                break;
            }
        }
        if (!exists){
            inventory.add(newItem);
        }
    }

    public void removeItem(Item removedItem, int quantity) {
        boolean exists = false;
        for (Item item : inventory) {
            if (item.getName().equals(removedItem.getName())) {
                exists = true;
                if (quantity > item.getQuantity()){
                    System.out.println("Tidak dapat mengurangi item sebanyak " + quantity +" buah");
                    break;
                } else{
                    item.decrease(quantity);
                    if (item.getQuantity() == 0) {
                        inventory.remove(item);
                    }
                    break;
                }
            }
        }
        if (!exists){
            System.out.println("Tidak ada item " + removedItem.getName() +" di inventory");
        }
    }

    public Furniture chooseFurniture(){
        System.out.println("Furnitur yang ada di inventory:");
        int count = 1;
        for (Item item : inventory){
            if (item instanceof Furniture){
                Furniture furniture = (Furniture) item;
                System.out.printf("%d. %s\n", count, furniture.getName());
                count++;
            }
        }
        if (count == 1){
            System.out.println("Tidak ada furnitur :<");
            return null;

        } else{
            String furnitureName;
            while (true){
                System.out.printf("\nMasukkan nama furnitur: ");
                furnitureName = scanner.nextLine();
                for (Item item : inventory){
                    if (furnitureName.toLowerCase().equals(item.getName().toLowerCase())){
                        removeItem(item, 1);
                        return FurnitureFactory.createFurniture(item.getName(), 1);
                    }
                }
                System.out.println("\nMasukkan nama furnitur yang valid");
            }
        }
    }

    public void displayInventory(){
        if(inventory.size() == 0){
            System.out.println(sim.getName() + " tidak memiliki apapun di inventory");
        } else{
            System.out.println("\nIsi inventory sim " + sim.getName() + ":");
            for (Item item : inventory){
                System.out.println("> " + item.getName() + " : " + item.getQuantity() + " buah");
            }
        }
    }
}
    // public Food chooseFood(){
    //     System.out.println("Makanan yang ada di inventory");
    // }

    // public void removeList(ArrayList<Item> arrayList){
    //     for (Item item : arrayList){
    //         removeItem(item, 1);
    //     }
    // }

    // public boolean checkContains(ArrayList<Item> arrayList){
    //     for (Item item : arrayList) {
    //         if (!inventory.contains(item)) {
    //             return false;
    //         }
    //     }
    //     return true;

    // }