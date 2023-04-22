package entity.item.furniture.stove;

public class EStove extends Stove{
    public EStove(int quantity){
        super(quantity, 1, 1, 200);
    }

    @Override
    public String getName(){
        return "Kompor Elektrik";
    }

    public static EStove getInstance(int quantity){
        return new EStove(quantity);
    }
}
