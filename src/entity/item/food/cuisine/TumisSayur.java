package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.ingredient.*;
import entity.item.food.ingredient.Ingredient;

public class TumisSayur extends Cuisine{
    static {
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.add(new Wortel(1));
        temp.add(new Bayam(1));
        setRecipe(temp);
    }
    
    public TumisSayur(int quantity) {
        super(quantity, 5);
    }

    @Override
    public String getName() {
        return "Tumis Sayur";
    }
}
