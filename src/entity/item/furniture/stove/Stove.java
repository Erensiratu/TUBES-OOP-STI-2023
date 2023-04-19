package entity.item.furniture.stove;

import entity.item.furniture.Furniture;

public abstract class Stove extends Furniture{
    public Stove(int quantity, int length, int width, int price){
        super(quantity, length, width, price);
    }

    /*public void cook(Cuisine cuisine){
        // Implementasi
    }*/

    public void use(int duration){
        // Cuisine cuisine;
        // this.cook(cuisine);
    }

    @Override
    public String getDescription(){
        return "Buat masak";
    }
}
