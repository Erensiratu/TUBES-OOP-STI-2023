import java.util.*;
public class SimPlicity {
    public static void main(String[] args) throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Bistik());
        items.add(new TumisSayur());
        System.out.println(items.get(0).getName());
        System.out.println(items.get(1).getName());
    }
}
