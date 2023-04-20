package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.ingredient.*;

public class NasiAyam extends Cuisine{
    static {
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.add(new Nasi(1));
        temp.add(new Ayam(1));
        setRecipe(temp);
    }
    
    public NasiAyam(int quantity) {
        super(quantity, 16);
    }

    @Override
    public String getName() {
        return "Nasi Ayam";
    }
    
}
