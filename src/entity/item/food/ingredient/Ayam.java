package entity.item.food.ingredient;

public class Ayam extends Ingredient{
    public Ayam(int quantity) {
        super(quantity, 10, 8);
    }

    @Override
    public String getName() {
        return "Ayam";
    }
}