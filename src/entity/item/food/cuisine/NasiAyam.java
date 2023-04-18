package entity.item.food.cuisine;

import java.util.ArrayList;
import java.util.Arrays;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class NasiAyam extends Cuisine{
    static Ingredient[] recipe = {new Nasi(1), new Ayam(1)};

    public NasiAyam(int quantity) {
        super(quantity, (ArrayList<Ingredient>) Arrays.asList(recipe), 16);
    }

    @Override
    public String getName() {
        return "Nasi Ayam";
    }
    
}
