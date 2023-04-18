package entity.item;

public abstract class Item{
    int quantity;

    // Kuantitas
    public Item(int quantity){
        this.quantity = quantity;
    }

    // Setter dan getter
    public int getQuantity(){
        return quantity;
    }

    public void add(int amount){
        quantity += amount;
    }

    public void decrease(int amount){
        quantity -= amount;
    }

    public abstract String getName();
}
