

public class Sapi extends Ingredient{
    public Sapi(int quantity) {
        super(quantity, 12, 15);
    }

    public static Sapi getInstance(int quantity){
        return new Sapi(quantity);
    }

    @Override
    public String getName() {
        return "Sapi";
    }
}
