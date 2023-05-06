

import java.util.ArrayList;


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
