package entity;


public class TumisSayur extends Cuisine{
    static{
    recipe.add(Wortel.getInstance(1));
    recipe.add(Bayam.getInstance(1));
    }
    
    public TumisSayur(int quantity) {
        super(quantity, 5);
    }

    public static TumisSayur getInstance(int quantity){
        return new TumisSayur(quantity);
    }

    @Override
    public String getName() {
        return "Tumis Sayur";
    }
}
