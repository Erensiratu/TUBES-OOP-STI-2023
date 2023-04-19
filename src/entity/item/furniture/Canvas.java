package entity.item.furniture;

import entity.sim.Sim;

public abstract class Canvas extends Furniture{
    public Canvas(int quantity){
        super(quantity, 2, 2, 200);
    }

    public void use(Sim sim, int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat gambar";
    }

    @Override
    public String getName(){
        return "Kanvas Lukis";
    }
}