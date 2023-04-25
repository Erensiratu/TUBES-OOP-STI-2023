package entity.item.food.ingredient;

public class Ayam extends Ingredient{
    public Ayam(int quantity) {
        super(quantity, 10, 8);
    }

    public static Ayam getInstance(int quantity){
        return new Ayam(quantity);
    }

    @Override
    public String getName() {
        return "Ayam";
    }
}