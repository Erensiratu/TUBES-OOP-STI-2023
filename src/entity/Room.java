package entity;

import java.util.*;

import entity.item.furniture.Furniture;

public class Room extends Furniture{
    private String name;
    private int width;
    private Furniture[][] grid;
    private Point locationRoom;
    private List<Furniture> objects;
    
    public Room(String name, int width, Point locationRoom) {
        this.name = name;
        this.width = width;
        this.grid = new Furniture[width][width];
        this.locationRoom = locationRoom;
        this.objects = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Point getLocation() {
        return this.locationRoom;
    }
    
    public void addObject(Furniture item, Point location) {
        if (location.x >= 0 && location.x < width && location.y >= 0 && location.y < width) {
            if (grid[location.x][location.y] == null) {
                grid[location.x][location.y] = item;
                item.setLocation(location);
                objects.add(item);
            } else {
                System.out.println("Cannot add furniture, location already occupied.");
            }
        } else {
            System.out.println("Cannot add furniture, location is out of bounds.");
        }
    }
    
    public void editObject(Furniture item) {
        if (objects.contains(item)) {
            // Edit furniture item
        } else {
            System.out.println("Furniture item not found in house.");
        }
    }
}
