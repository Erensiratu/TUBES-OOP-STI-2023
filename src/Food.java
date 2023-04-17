import java.util.ArrayList;

public abstract class Food extends Item{
    ArrayList<FoodMaterial> ingredients = new ArrayList<>();
    
    public Food(){
        super(1);
    }
}
