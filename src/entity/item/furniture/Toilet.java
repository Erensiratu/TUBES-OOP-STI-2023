package entity.item.furniture;

public abstract class Toilet extends Furniture{
    public Toilet(int quantity){
        super(quantity, 1, 1, 50);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat buang air";
    }

    @Override
    public String getName(){
        return "Toilet";
    }
}
