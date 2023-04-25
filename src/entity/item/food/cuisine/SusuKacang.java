package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class SusuKacang extends Cuisine{
    static {
        recipe.add(Susu.getInstance(1));
        recipe.add(Kacang.getInstance(1));
    }
    
    public SusuKacang(int quantity) {
        super(quantity, 5);
    }

    public static SusuKacang getInstance(int quantity){
        return new SusuKacang(quantity);
    }

    @Override
    public String getName() {
        return "Susu Kacang";
    }
}
