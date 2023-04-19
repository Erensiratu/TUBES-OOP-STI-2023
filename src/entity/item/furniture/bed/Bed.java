package entity.item.furniture.bed;

import entity.item.furniture.Furniture;
import entity.sim.Sim;
import entity.sim.Status;

public abstract class Bed extends Furniture{

    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void sleep(int duration){
        // Implementasi
        // duration dalam detik
        if(duration < 180){
            System.out.println("sleep duration should be more than 3 minutes");
        } else {
            Sim sim;
            sim.getStatus().addMood(duration*(0.125));
            sim.getStatus().addHealth(duration*(0.0833));
        }
    }

    public void use(Sim sim, int duration){
        // int duration;
        // this.sleep(duration);
    }

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
