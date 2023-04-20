package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class NasiAyam extends Cuisine{
    static {
        recipe.add(new Nasi(1));
        recipe.add(new Ayam(1));
    }
    
    public NasiAyam(int quantity) {
        super(quantity, 16);
    }

    @Override
    public String getName() {
        return "Nasi Ayam";
    }
    
}
