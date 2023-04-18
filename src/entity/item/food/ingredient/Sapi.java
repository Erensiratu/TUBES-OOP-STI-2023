package entity.item.food.ingredient;

public class Sapi extends Ingredient{
    public Sapi(int quantity) {
        super(quantity, 12, 15);
    }

    @Override
    public String getName() {
        return "Sapi";
    }
}