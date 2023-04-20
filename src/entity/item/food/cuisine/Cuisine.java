package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.Food;
import entity.item.food.ingredient.Ingredient;

public abstract class Cuisine extends Food{
    protected static ArrayList<Ingredient> recipe = new ArrayList<>();
    
    public Cuisine(int quantity, int fullness) {
        super(quantity, fullness);
    }

    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }

    public float getCookingTime(){
        return (float) (1.5 * getFullness());
    }
}
