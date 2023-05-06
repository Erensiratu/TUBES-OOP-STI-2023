

import java.util.Random;


/*
 * Kelas abstrak untuk Ingredient
 * Kontributor: 
 * 1. Haidar - 18221134
 * 
 * Atribut:
 * - price
 * 
 * Method:
 * + [static] [function] createIngredient(String name, int quantity) -> Ingredient
 */

public abstract class Ingredient extends Food implements Purchaseable{
    // Attributes
    private int price;

    // Methods
    // Constructor:
    public Ingredient(int quantity, int price, int fullness) {
        super(quantity, fullness);
        this.price = price;
    }

    // Purchaseable.java Implementation:
    @Override
    public int getDelliveryTime(){
        return 30 * (new Random().nextInt(MAX - MIN + 1) + MIN);
    }
    
    @Override
    public int getPrice(){
        return price;
    }
}
