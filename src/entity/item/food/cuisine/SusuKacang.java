package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class SusuKacang extends Cuisine{
    static {
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.add(new Susu(1));
        temp.add(new Kacang(1));
        setRecipe(temp);
    }
    
    public SusuKacang(int quantity) {
        super(quantity, 5);
    }

    @Override
    public String getName() {
        return "Susu Kacang";
    }
}
