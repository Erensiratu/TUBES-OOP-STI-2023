package entity.item.food.cuisine;

import java.util.ArrayList;
import java.util.Arrays;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class SusuKacang extends Cuisine{
    static Ingredient[] recipe = {new Susu(1), new Kacang(1)};

    public SusuKacang(int quantity) {
        super(quantity, (ArrayList<Ingredient>) Arrays.asList(recipe), 16);
    }

    @Override
    public String getName() {
        return "Susu Kacang";
    }
}
