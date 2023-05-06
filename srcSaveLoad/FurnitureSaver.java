import java.io.Serializable;

public class FurnitureSaver implements Serializable{
    private Point location;
    private String name;
    
    public Point getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public FurnitureSaver(String s, Point p){
        name = s;
        location = p;
    }
}