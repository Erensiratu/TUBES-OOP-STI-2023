package entity.item.furniture.bed;

public class KingBed extends Bed{
    public KingBed(int quantity){
        super(quantity, 4, 2, 150);
    }

    @Override
    public String getName(){
        return "Kasur King Size";
    }
}
