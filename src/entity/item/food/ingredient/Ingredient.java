package entity.item.food.ingredient;

import java.util.Random;

import entity.item.Purchaseable;
import entity.item.food.Food;

public abstract class Ingredient extends Food implements Purchaseable{
    private int price;

    public Ingredient(int quantity, int price, int fullness) {
        super(quantity, fullness);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public int getDelliveryTime(){
        return 30 * (new Random().nextInt(MAX - MIN + 1) + MIN);
    }

    
}
