package entity.item.furniture.stove;

public class EStove extends Stove{
    public EStove(int quantity){
        super(quantity, 1, 1, 200);
    }

    public static EStove getInstance(int quantity){
        return new EStove(quantity);
    }

    @Override
    public String getName(){
        return "Kompor Elektrik";
    }
}
