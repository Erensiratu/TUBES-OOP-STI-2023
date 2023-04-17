abstract class Item {
    int quantity;

    public Item(int quantity){
        this.quantity = quantity;
    }

    public abstract String getName();

    public int getQuantity(){
        return quantity;
    }

    public void add(){
        quantity++;
    }
    
    public void remove(){
        quantity--;
    }
}