package entity.item.food.ingredient;

public class Nasi extends Ingredient{
    public Nasi(int quantity) {
        super(quantity, 5, 5);
    }

    public static Nasi getInstance(int quantity){
        return new Nasi(quantity);
    }

    @Override
    public String getName() {
        return "Nasi";
    }
}
