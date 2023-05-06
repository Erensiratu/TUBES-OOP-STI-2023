

public class Bayam extends Ingredient{
    public Bayam(int quantity) {
        super(quantity, 3, 2);
    }

    public static Bayam getInstance(int quantity){
        return new Bayam(quantity);
    }

    @Override
    public String getName() {
        return "Bayam";
    }
}