package entity.item.food.ingredient;

public class Susu extends Ingredient{
    public Susu(int quantity) {
        super(quantity, 2, 1);
    }

    @Override
    public String getName() {
        return "Susu";
    }

    public static Susu getInstance(int quantity){
        return new Susu(quantity);
    }
}