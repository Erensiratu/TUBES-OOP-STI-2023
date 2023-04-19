package entity.item.furniture;

public abstract class Easel extends Furniture{
    public Easel(int quantity){
        super(quantity, 2, 1, 20);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat menggambar";
    }

    @Override
    public String getName(){
        return "Sandaran Canvas";
    }
}
