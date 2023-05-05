



public class NasiAyam extends Cuisine{
    static {
        recipe.add(Nasi.getInstance(1));
        recipe.add(Ayam.getInstance(1));
    }
    
    public NasiAyam(int quantity) {
        super(quantity, 16);
    }

    public static NasiAyam getInstance(int quantity){
        return new NasiAyam(quantity);
    }

    @Override
    public String getName() {
        return "Nasi Ayam";
    }
}
