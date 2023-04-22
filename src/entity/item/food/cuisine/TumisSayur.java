package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class TumisSayur extends Cuisine{
    static{
    recipe.add(new Wortel(1));
    recipe.add(new Bayam(1));
    }
    
    public TumisSayur(int quantity) {
        super(quantity, 5);
    }

    @Override
    public String getName() {
        return "Tumis Sayur";
    }

    public static TumisSayur getInstance(int quantity){
        return new TumisSayur(quantity);
    }
}
