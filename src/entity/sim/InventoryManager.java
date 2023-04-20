package entity.sim;

import java.util.ArrayList;

import entity.item.Item;

public class InventoryManager {
    ArrayList<Item> inventory = new ArrayList<>();

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