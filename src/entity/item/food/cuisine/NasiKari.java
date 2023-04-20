package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class NasiKari extends Cuisine{
    static {
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.add(new Nasi(1));
        temp.add(new Ayam(1));
        temp.add(new Wortel(1));
        temp.add(new Sapi(1));
        setRecipe(temp);
    }

    public NasiKari(int quantity) {
        super(quantity, 30);
    }

    @Override
    public String getName() {
        return "Nasi Kari";
    }
}
