package entity.item.furniture;

public abstract class Canvas extends Furniture{
    public Canvas(int quantity){
        super(quantity, 2, 2, 200);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat gambar";
    }

    @Override
    public String getName(){
        return "Kanvas Lukis";
    }
}