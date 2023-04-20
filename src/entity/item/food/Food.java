package entity.item.food;

import entity.item.Item;

public abstract class Food extends Item{
    int fullness;

    public Food(int quantity, int fullness){
        super(quantity);
        this.fullness = fullness;
    }

    public int getFullness() {
        return fullness;
    }
}
