package entity.item.furniture;

import entity.sim.Sim;

public class Clock extends Furniture{
    public Clock(int quantity){
        super(quantity, 1, 1, 10);
    }

    public static Clock getInstance(int quantity){
        return new Clock(quantity);
    }

    public void use(Sim sim){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat melihat waktu";
    }

    @Override
    public String getName(){
        return "Jam";
    }
}
