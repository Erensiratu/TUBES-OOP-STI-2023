package entity.item.food.cuisine;

import java.util.ArrayList;
import java.util.Arrays;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class TumisSayur extends Cuisine{
    static Ingredient[] recipe = {new Wortel(1), new Bayam(1)};

    public TumisSayur(int quantity) {
        super(quantity, (ArrayList<Ingredient>) Arrays.asList(recipe), 16);
    }

    @Override
    public String getName() {
        return "Tumis Sayur";
    }
}
