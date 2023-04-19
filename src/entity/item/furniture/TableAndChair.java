package entity.item.furniture;

import entity.sim.Sim;

public abstract class TableAndChair extends Furniture{
    public TableAndChair(int quantity){
        super(quantity, 3, 3, 50);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat makan";
    }

    @Override
    public String getName(){
        return "Meja Dan Kursi";
    }
}
