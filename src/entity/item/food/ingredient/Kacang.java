package entity.item.food.ingredient;

public class Kacang extends Ingredient{
    public Kacang(int quantity) {
        super(quantity, 2, 2);
    }

    @Override
    public String getName() {
        return "Kacang";
    }

    public static Kacang getInstance(int quantity){
        return new Kacang(quantity);
    }
}