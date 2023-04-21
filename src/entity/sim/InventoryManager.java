package entity.sim;

import java.util.ArrayList;
import java.util.Scanner;

import entity.item.Item;
import entity.item.food.Food;
import entity.item.furniture.Furniture;

public class InventoryManager {
    ArrayList<Item> inventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

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
            if (item.equals(removedItem)) {
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
        System.out.println("Furnitur yang ada di inventory");
        int count = 1;
        for (Item item : inventory){
            if (item instanceof Furniture){
                Furniture furniture = (Furniture) item;
                System.out.printf("%d. %s\n", count, furniture.getName());
                count++;
            }
        }

        if (count == 1){
            System.out.println("Tidak ada furnitur");
            return null;
        } else{
            System.out.printf("\nMasukkan nama furnitur: ");
            String furnitureName = scanner.nextLine();

            while (true){
                System.out.printf("\nTidak ada furnitur dengan nama %s\nMasukkan nama furnitur: ", furnitureName);
                furnitureName = scanner.nextLine();
                for (Item item : inventory){
                    if (furnitureName.equals(item.getName())){
                        removeItem(item, 1);
                        return (Furniture) item;
                    }
                }
            }
        }
    }

    // public Food chooseFood(){
    //     System.out.println("Makanan yang ada di inventory");
    // }

    public void removeList(ArrayList<Item> arrayList){
        for (Item item : arrayList){
            removeItem(item, 1);
        }
    }

    public boolean checkContains(ArrayList<Item> arrayList){
        for (Item item : arrayList) {
            if (!inventory.contains(item)) {
                return false;
            }
        }
        return true;

    }
}