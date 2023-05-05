

public class Wortel extends Ingredient{
    public Wortel(int quantity) {
        super(quantity, 3, 2);
    }

    public static Wortel getInstance(int quantity){
        return new Wortel(quantity);
    }

    @Override
    public String getName() {
        return "Wortel";
    }
}