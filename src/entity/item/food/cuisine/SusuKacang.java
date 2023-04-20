package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.Item;
import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class SusuKacang extends Cuisine{
    static {
        recipe.add(new Susu(1));
        recipe.add(new Kacang(1));
    }
    
    public SusuKacang(int quantity) {
        super(quantity, 5);
    }

    @Override
    public String getName() {
        return "Susu Kacang";
    }
}
