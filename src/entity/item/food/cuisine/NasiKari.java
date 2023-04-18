package entity.item.food.cuisine;

import java.util.ArrayList;
import java.util.Arrays;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class NasiKari extends Cuisine{
    static Ingredient[] recipe = {new Nasi(1), new Ayam(1), new Wortel(1), new Sapi(1)};

    public NasiKari(int quantity) {
        super(quantity, (ArrayList<Ingredient>) Arrays.asList(recipe), 16);
    }

    @Override
    public String getName() {
        return "Nasi Kari";
    }
}
