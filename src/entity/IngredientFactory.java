package entity;

/*
 * Kelas untuk membuat instance dari kelas Ingredient
 * Kontributor: 
 * 1. Haidar - 18221134
 * 
 * Atribut:
 * 
 * Method:
 * + [static] [function] createIngredient(String name, int quantity) -> Ingredient
 * 
 */

public class IngredientFactory {
    public static Ingredient createIngredient(String name, int quantity) {
        switch (name.toLowerCase()) {
            case "ayam":
                return Ayam.getInstance(quantity);
            case "bayam":
                return Bayam.getInstance(quantity);
            case "kacang":
                return Kacang.getInstance(quantity);
            case "kentang":
                return Kentang.getInstance(quantity);
            case "nasi":
                return Nasi.getInstance(quantity);
            case "sapi":
                return Sapi.getInstance(quantity);
            case "susu":
                return Susu.getInstance(quantity);
            case "wortel":
                return Wortel.getInstance(quantity);
            default:
                return null;
        }
    }
}