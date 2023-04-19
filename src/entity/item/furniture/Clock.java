package entity.item.furniture;

public abstract class Clock extends Furniture{
    public Clock(int quantity){
        super(quantity, 1, 1, 10);
    }

    public void use(int duration){
        // Implementasi
    }

    @Override
    public String getDescription(){
        return "Buat melihat waktu";
    }

    @Override
    public String getName(){
        return "Jam";
    }
}
