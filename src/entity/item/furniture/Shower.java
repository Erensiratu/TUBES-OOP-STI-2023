package entity.item.furniture;

import entity.sim.Sim;

public abstract class Shower extends Furniture{
    public Shower(int quantity){
        super(quantity, 2, 1, 100);
    }

    public void use(Sim sim, int duration){
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
