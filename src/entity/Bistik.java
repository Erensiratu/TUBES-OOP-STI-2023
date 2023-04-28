package entity;


public class Bistik extends Cuisine{
    static {
        recipe.add(Kentang.getInstance(1));
        recipe.add(Sapi.getInstance(1));
    }

    public Bistik(int quantity) {
        super(quantity, 22);
    }

    public static Bistik getInstance(int quantity){
        return new Bistik(quantity);
    }

    @Override
    public String getName() {
        return "Bistik";
    }
}
