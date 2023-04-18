package entity.item.furniture.stove;

public class GasStove extends Stove{
    public GasStove(int quantity){
        super(quantity, 1, 2, 100);
    }

    @Override
    public String getName(){
        return "Kompor Gas";
    }
}
