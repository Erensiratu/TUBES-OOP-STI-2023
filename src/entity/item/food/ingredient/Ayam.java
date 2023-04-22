package entity.item.food.ingredient;

import entity.item.Item;

public class Ayam extends Ingredient{
    public Ayam(int quantity) {
        super(quantity, 10, 8);
    }

    @Override
    public String getName() {
        return "Ayam";
    }

    public static Ayam getInstance(int quantity){
        return new Ayam(quantity);
    }
}