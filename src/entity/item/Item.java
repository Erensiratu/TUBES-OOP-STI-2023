package entity.item;

public abstract class Item implements Cloneable{
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

    // @Override
    // public Object clone() throws CloneNotSupportedException {
    //     Item clonedObject = (Item) super.clone();
    //     // Create a new object for the mutable field
    //     clonedObject.myField = new Integer(myField);
    //     return clonedObject;
    // }
}
