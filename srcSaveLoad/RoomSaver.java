import java.io.Serializable;
import java.util.ArrayList;

public class RoomSaver implements Serializable{
    private String name;
    private Point locationRoom;
    private ArrayList<FurnitureSaver> objects = new  ArrayList<FurnitureSaver>();

    public String getName() {
        return name;
    }

    public Point getLocationRoom() {
        return locationRoom;
    }

    public ArrayList<FurnitureSaver> getObjects() {
        return objects;
    }

    public RoomSaver(Room r){
        name = r.getName();
        locationRoom = r.getLocation();
        for(Furniture i : r.getObjectList()){
            FurnitureSaver j = new FurnitureSaver(i.getName(), i.getPoint());
            objects.add(j);
        }
    }
}