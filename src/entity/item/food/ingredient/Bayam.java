package entity.item.food.ingredient;

import entity.item.Item;

public class Bayam extends Ingredient{
    public Bayam(int quantity) {
        super(quantity, 3, 2);
    }

    @Override
    public String getName() {
        return "Bayam";
    }

    public static Bayam getInstance(int quantity){
        return new Bayam(quantity);
    }
}