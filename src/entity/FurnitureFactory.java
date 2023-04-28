package entity;

public class FurnitureFactory {
    public static Furniture createFurniture(String name, int quantity) {
        switch (name.toLowerCase()) {
            case "kasur single":
                return SingleBed.getInstance(quantity);
            case "kasur queen":
                return QueenBed.getInstance(quantity);
            case "kasur king":
                return KingBed.getInstance(quantity);
            case "kompor gas":
                return GasStove.getInstance(quantity);
            case "kompor listrik":
                return EStove.getInstance(quantity);
            case "kanvas":
                return Canvas.getInstance(quantity);
            case "jam":
                return Clock.getInstance(quantity);
            case "toilet":
                return Toilet.getInstance(quantity);
            case "shower":
                return Shower.getInstance(quantity);
            case "kursi":
                return TableAndChair.getInstance(quantity);
            case "meja":
                return TableAndChair.getInstance(quantity);
            case "kursi dan meja":
                return TableAndChair.getInstance(quantity);
            case "meja dan kursi":
                return TableAndChair.getInstance(quantity);
            default:
                return null;
        }
    }
}
