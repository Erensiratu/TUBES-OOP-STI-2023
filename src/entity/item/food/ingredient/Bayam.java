package entity.item.food.ingredient;

public class Bayam extends Ingredient{
    public Bayam(int quantity) {
        super(quantity, 3, 2);
    }

    @Override
    public String getName() {
        return "Bayam";
    }
}