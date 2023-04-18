package entity.item.food.ingredient;

public class Nasi extends Ingredient{
    public Nasi(int quantity) {
        super(quantity, 5, 5);
    }

    @Override
    public String getName() {
        return "Nasi";
    }
}
