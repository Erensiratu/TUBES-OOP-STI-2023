package entity;

import java.util.*;

import entity.item.furniture.Furniture;
import entity.sim.Sim;

public class Room {
    private int roomWidth = 6;
    private String name;
    private Furniture[][] grid;
    private Point locationRoom;
    private ArrayList<Furniture> objects;
    Scanner scanner = new Scanner(System.in);
    
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

    public void addObject(Furniture object) throws RoomException {
        int x,y;
        System.out.printf("\nMasukkan x: ");
        x = scanner.nextInt();
        System.out.printf("\nMasukkan y: ");
        y = scanner.nextInt();

        Point location = new Point(x, y);

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

        // No collision
        for (int i = location.getX(); i < location.getX() + objectWidth; i++) {
            for (int j = location.getY(); j < location.getY() + objectLength; j++) {
                grid[i][j] = object;
            }
        }

        object.setLocation(location);

        objects.add(object);
    }

    public void removeObject(Furniture object) {
        Point location = object.getPoint();
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
    
        for (int i = location.getX(); i < location.getX() + objectWidth; i++) {
            for (int j = location.getY(); j < location.getY() + objectLength; j++) {
                grid[i][j] = null;
            }
        }
    
        objects.remove(object);
    }

    public void editRoom(Sim sim){
        System.out.printf("\nPilih aksi:\n1. Letakkan Objek\n2. Pindahkan Objek\n3. Ambil Objek\nNomor aksi:");
        int x = scanner.nextInt();
        while ((x < 1)||(x > 3)){
            System.out.printf("\n\nSilakan ulangi input: ");
            x = scanner.nextInt();
        }

        if (x == 1){
            Furniture furniture = sim.getInventory().chooseFurniture();
            if (furniture != null){
                printRoom();
                try {
                    this.addObject(furniture);
                } catch (RoomException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void printRoom() {
        System.out.print("\nTampilan ruangan:\n");
        for (int i = 0; i < roomWidth; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < roomWidth; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < roomWidth; j++) {
                if (grid[i][j] == null) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[-]");
                }
            }
            System.out.printf("\n\n");
        }
    }
}
