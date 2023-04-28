package entity;

public class CuisineFactory {
    public static Cuisine createCuisine(String name, int quantity) {
        switch (name.toLowerCase()) {
            case "bistik":
                return Bistik.getInstance(quantity);
            case "nasi ayam":
                return NasiAyam.getInstance(quantity);
            case "nasi kari":
                return NasiKari.getInstance(quantity);
            case "susu kacang":
                return SusuKacang.getInstance(quantity);
            case "tumis sayur":
                return TumisSayur.getInstance(quantity);
            default:
                return null;
        }
    }
}
