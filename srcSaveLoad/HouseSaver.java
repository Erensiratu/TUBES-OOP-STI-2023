import java.io.Serializable;
import java.util.ArrayList;


public class HouseSaver implements Serializable{
    private Point location;
    private ArrayList<RoomSaver> rooms = new ArrayList<RoomSaver>();
    private boolean upgrading ;
    RoomSaver primaryRoom;

    public Point getLocation() {
        return location;
    }

    public ArrayList<RoomSaver> getRooms() {
        return rooms;
    }

    public boolean isUpgrading() {
        return upgrading;
    }

    public RoomSaver getPrimaryRoom() {
        return primaryRoom;
    }

    public HouseSaver(House h){
        location = h.getLocation();
        upgrading = h.isUpgrading();
        for (Room i : h.getRoomList()){
            RoomSaver j = new RoomSaver(i);
            rooms.add(j);
        }
        primaryRoom = new RoomSaver(h.getPrimaryRoom());

    }
}