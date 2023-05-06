

public class SingleBed extends Bed{
    public SingleBed(int quantity){
        super(quantity, 4, 1, 50);
    }
    
    public static SingleBed getInstance(int quantity){
        return new SingleBed(quantity);
    }

    @Override
    public String getName(){
        return "Kasur Single";
    }
}
