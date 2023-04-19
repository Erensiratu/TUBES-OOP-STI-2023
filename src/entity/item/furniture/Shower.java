package entity.item.furniture;

public abstract class Shower extends Furniture{
    public Shower(int quantity){
        super(quantity, 2, 1, 100);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat mandi";
    }

    @Override
    public String getName(){
        return "Shower";
    }
}
