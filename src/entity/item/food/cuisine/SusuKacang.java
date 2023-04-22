package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class SusuKacang extends Cuisine{
    static {
        recipe.add(new Susu(1));
        recipe.add(new Kacang(1));
    }
    
    public SusuKacang(int quantity) {
        super(quantity, 5);
    }

    @Override
    public String getName() {
        return "Susu Kacang";
    }
}
