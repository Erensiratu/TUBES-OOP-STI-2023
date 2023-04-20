package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.Food;
import entity.item.food.ingredient.Ingredient;

public abstract class Cuisine extends Food{
    private static ArrayList<Ingredient> recipe;
    
    public Cuisine(int quantity, int fullness) {
        super(quantity, fullness);
    }

    public static ArrayList<Ingredient> getRecipe() {
        return recipe;
    }

    public static void setRecipe(ArrayList<Ingredient> recipe){
        Food.recipe = recipe;
    }

    public float getCookingTime(){
        return (float) (1.5 * getFullness());
    }

    //public boolean
}
