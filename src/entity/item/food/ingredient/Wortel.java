package entity.item.food.ingredient;

public class Wortel extends Ingredient{
    public Wortel(int quantity) {
        super(quantity, 3, 2);
    }

    @Override
    public String getName() {
        return "Wortel";
    }

    public static Wortel getInstance(int quantity){
        return new Wortel(quantity);
    }
}