package entity.item.food.ingredient;

public class Kacang extends Ingredient{
    public Kacang(int quantity) {
        super(quantity, 2, 2);
    }

    @Override
    public String getName() {
        return "Kacang";
    }
}