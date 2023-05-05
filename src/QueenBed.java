

public class QueenBed extends Bed{
    public QueenBed(int quantity){
        super(quantity, 4, 2, 100);
    }

    public static QueenBed getInstance(int quantity){
        return new QueenBed(quantity);
    }

    @Override
    public String getName(){
        return "Kasur Queen Size";
    }
}
