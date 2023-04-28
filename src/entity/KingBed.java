package entity;

public class KingBed extends Bed{
    public KingBed(int quantity){
        super(quantity, 5, 2, 150);
    }

    public static KingBed getInstance(int quantity){
        return new KingBed(quantity);
    }
    
    @Override
    public String getName(){
        return "Kasur King Size";
    }
}
