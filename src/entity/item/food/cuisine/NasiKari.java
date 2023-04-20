package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.Item;
import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class NasiKari extends Cuisine{
    static {
        recipe.add(new Nasi(1));
        recipe.add(new Ayam(1));
        recipe.add(new Wortel(1));
        recipe.add(new Sapi(1));
    }

    public NasiKari(int quantity) {
        super(quantity, 30);
    }

    @Override
    public String getName() {
        return "Nasi Kari";
    }
}
