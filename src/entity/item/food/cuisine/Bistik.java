package entity.item.food.cuisine;

import java.util.ArrayList;

import entity.item.Item;
import entity.item.food.ingredient.*;

public class Bistik extends Cuisine{
    static {
        recipe.add(new Kentang(1));
        recipe.add(new Sapi(1));
    }

    public Bistik(int quantity) {
        super(quantity, 22);
    }

    @Override
    public String getName() {
        return "Bistik";
    }
}
