package entity.item.furniture;

import entity.Sim;

public abstract class Toilet extends Furniture{
    public Toilet(int quantity){
        super(quantity, 1, 1, 50);
    }

    public void use(Sim sim){
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