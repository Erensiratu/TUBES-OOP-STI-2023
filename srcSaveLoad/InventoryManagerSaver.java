import java.io.Serializable;
import java.util.ArrayList;

public class InventoryManagerSaver implements Serializable{
    private ArrayList<ItemSaver> inventory = new ArrayList<ItemSaver>();

    public InventoryManagerSaver(InventoryManager inventory){
        for(Item i: inventory.getList()){
            ItemSaver j = new ItemSaver(i.getName(), i.getQuantity());
            this.inventory.add(j);
        }
    }
    public ArrayList<ItemSaver> getInventory() {
        return inventory;
    }




}