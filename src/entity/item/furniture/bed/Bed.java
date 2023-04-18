package entity.item.furniture.bed;

import entity.item.furniture.Furniture;
import entity.Sim;

public abstract class Bed extends Furniture{
    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void sleep(int duration){
        // Implementasi
    }

    public void use(Sim sim){
        // int duration;
        // this.sleep(duration);
    }

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
