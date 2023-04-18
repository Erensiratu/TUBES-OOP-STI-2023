package entity.item.furniture.bed;

public class SingleBed extends Bed{
    public SingleBed(int quantity){
        super(quantity, 4, 1, 50);
    }

    @Override
    public String getName(){
        return "Kasur Single";
    }
}
