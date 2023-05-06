import java.io.Serializable;



public class ItemSaver implements Serializable {
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemSaver(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    
    // private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
    //     aOutputStream.writeUTF(name);
    //     aOutputStream.writeInt(quantity);
    // }

    // private void readObject(ObjectInputStream aInputStream) throws IOException, ClassNotFoundException {
    //     x = aInputStream.readInt();
    //     y = aInputStream.readInt();
    // }
}