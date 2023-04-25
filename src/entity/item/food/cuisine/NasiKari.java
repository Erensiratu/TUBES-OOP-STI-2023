package entity.item.food.cuisine;

import entity.item.food.ingredient.*;

public class NasiKari extends Cuisine{
    static {
        recipe.add(Nasi.getInstance(1));
        recipe.add(Ayam.getInstance(1));
        recipe.add(Wortel.getInstance(1));
        recipe.add(Sapi.getInstance(1));
    }

    public NasiKari(int quantity) {
        super(quantity, 30);
    }

    public static NasiKari getInstance(int quantity){
        return new NasiKari(quantity);
    }

    @Override
    public String getName() {
        return "Nasi Kari";
    }
}
