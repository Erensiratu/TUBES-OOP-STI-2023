package entity.item.food.ingredient;

public class Kentang extends Ingredient{
    public Kentang(int quantity) {
        super(quantity, 3, 4);
    }

    @Override
    public String getName() {
        return "Kentang";
    }

    public static Kentang getInstance(int quantity){
        return new Kentang(quantity);
    }
}