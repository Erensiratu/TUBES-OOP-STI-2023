package entity.item.furniture;

import entity.sim.Sim;

public abstract class Toilet extends Furniture{
    public Toilet(int quantity){
        super(quantity, 1, 1, 50);
    }

    public void use(Sim sim, int duration){
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
