

public class Kacang extends Ingredient{
    public Kacang(int quantity) {
        super(quantity, 2, 2);
    }

    public static Kacang getInstance(int quantity){
        return new Kacang(quantity);
    }

    @Override
    public String getName() {
        return "Kacang";
    }
}