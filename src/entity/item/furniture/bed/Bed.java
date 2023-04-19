package entity.item.furniture.bed;

import java.util.Scanner;

import entity.item.furniture.Furniture;
import entity.sim.Sim;

public abstract class Bed extends Furniture{
    Scanner scanner = new Scanner(System.in);

    public Bed(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    public void sleep(Sim sim){
        // Implementasi
        int duration = scanner.nextInt();
        duration *= 1000;
        // duration dalam detik
        if(duration < 180){
            System.out.println("sleep duration should be more than 3 minutes");
        } else {
            sim.getStatus().addMood(duration*(0.125));
            sim.getStatus().addHealth(duration*(0.0833));
        }
    }

    @Override
    public void use(Sim sim){
        sleep(sim);
    }

    @Override
    public String getDescription(){
        return "Buat tidur :3";
    }
}
