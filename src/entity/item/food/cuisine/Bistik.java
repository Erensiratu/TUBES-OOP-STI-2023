package entity.item.food.cuisine;

import java.util.ArrayList;
import java.util.Arrays;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class Bistik extends Cuisine{
    static Ingredient[] recipe = {new Kentang(1), new Sapi(1)};

    public Bistik(int quantity) {
        super(quantity, (ArrayList<Ingredient>) Arrays.asList(recipe), 16);
    }

    @Override
    public String getName() {
        return "Bistik";
    }
}
