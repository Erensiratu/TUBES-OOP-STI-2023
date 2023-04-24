package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class NasiKari extends Cuisine{
    static {
        recipe.add(new Nasi(1));
        recipe.add(new Ayam(1));
        recipe.add(new Wortel(1));
        recipe.add(new Sapi(1));
    }

    public NasiKari(int quantity) {
        super(quantity, 30);
    }

    @Override
    public String getName() {
        return "Nasi Kari";
    }

    public static NasiKari getInstance(int quantity){
        return new NasiKari(quantity);
    }
}
