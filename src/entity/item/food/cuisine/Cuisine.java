package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.Food;
import entity.item.food.ingredient.Ingredient;

public abstract class Cuisine extends Food{
    private ArrayList<Ingredient> ingredients;
    
    public Cuisine(int quantity, ArrayList<Ingredient> ingredients, int fullness) {
        super(quantity, fullness);
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public float getCookingTime(){
        return (float) (1.5 * getFullness());
    }

    //public boolean
}
