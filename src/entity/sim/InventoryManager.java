package entity.sim;

import java.util.ArrayList;

import entity.item.Item;
import entity.item.food.ingredient.Ingredient;

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

    public void removeItem(Item item){
        
    }

    public boolean checkContains(ArrayList<Ingredient> arrayList){
        for (Item item : arrayList) {
            if (!inventory.contains(item)) {
                return false;
            }
        }
        return true;

    }
}
