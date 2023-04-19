package entity;

import java.util.*;

public class House {
    private String name;
    private Point location;
    private List<Room> rooms;
    
    public House(String name, Point location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public void addRoom(Room room) {
        this.rooms.add(room);
    }
}
