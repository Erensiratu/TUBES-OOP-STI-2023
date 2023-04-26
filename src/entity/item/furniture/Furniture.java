package entity.item.furniture;

import java.util.Random;
import entity.item.Item;
import entity.item.Purchaseable;
import entity.item.Useable;
import entity.Point;
import entity.sim.Sim;

public abstract class Furniture extends Item implements Useable, Purchaseable{
    private int length;
    private int width;
    private int price;
    private Point location;
    private boolean rotated;
    private boolean vacant;

    public Furniture(int quantity, int length, int width, int price){
        super(quantity);
        this.length = length;
        this.width = width;
        this.price = price;
        this.location = null;
        this.rotated = false;
        this.vacant = true;
    }

    public abstract void use(Sim sim);

    public int getPrice(){
        return price;
    }

    public int getDelliveryTime(){
        return 30 * (new Random().nextInt(MAX - MIN + 1) + MIN);
    }

    public int getLength(){
        return length;
    }

    public boolean getVacancy(){
        return vacant;
    }

    public int getWidth(){
        return width;
    }

    public Point getPoint(){
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isRotated(){
        return rotated;
    }

    public boolean isVacant(){
        return vacant;
    }

    public void setRotation(boolean rotation){
        this.rotated = rotation;
    }

    public void setVacancy(boolean vacant){
        this.vacant = vacant;
    }

    public abstract String getDescription();


}
