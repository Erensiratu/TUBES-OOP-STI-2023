package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.food.ingredient.*;

public class Bistik extends Cuisine{
    static {
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.add(new Kentang(1));
        temp.add(new Sapi(1));
        setRecipe(temp);
    }

    public Bistik(int quantity) {
        super(quantity, 22);
    }

    @Override
    public String getName() {
        return "Bistik";
    }
}
