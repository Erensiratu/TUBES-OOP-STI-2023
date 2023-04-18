package entity.item.furniture.bed;

public class QueenBed extends Bed{
    public QueenBed(int quantity){
        super(quantity, 4, 2, 100);
    }

    @Override
    public String getName(){
        return "Kasur Queen Size";
    }
}
