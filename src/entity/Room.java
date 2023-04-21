package entity;

import java.util.*;

import entity.item.furniture.Furniture;

public class Room {
    private int roomWidth = 6;
    private String name;
    private Furniture[][] grid;
    private Point locationRoom;
    private ArrayList<Furniture> objects;
    
    public Room(String name, Point locationRoom) {
        this.name = name;
        this.grid = new Furniture[roomWidth][roomWidth];
        this.locationRoom = locationRoom;
        this.objects = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Point getLocation() {
        return this.locationRoom;
    }

    public void addObject(Furniture object, Point location) throws RoomException {
        int objectWidth, objectLength;
        if (object.getRotation()) {
            // object is rotated
            objectWidth = object.getLength();
            objectLength = object.getWidth();
        } else {
            // object is not rotated
            objectWidth = object.getWidth();
            objectLength = object.getLength();
        }

        if (location.getX() + objectWidth > roomWidth || location.getY() + objectLength > roomWidth) {
            throw new RoomException("object is out of bounds");
        }

        for (int i = location.getX(); i < location.getX() + objectWidth; i++) {
            for (int j = location.getY(); j < location.getY() + objectLength; j++) {
                if (grid[i][j] != null) {
                    throw new RoomException("Collision detected");
                }
            }
        }

        // No collision, add the object to the grid
        for (int i = location.getX(); i < location.getX() + objectWidth; i++) {
            for (int j = location.getY(); j < location.getY() + objectLength; j++) {
                grid[i][j] = object;
            }
        }

        object.setLocation(location);

        objects.add(object);
    }
    
    public void editObject(Furniture item) {
        if (objects.contains(item)) {
            // Edit furniture item
        } else {
            System.out.println("Furniture item not found in house.");
        }
    }
}
