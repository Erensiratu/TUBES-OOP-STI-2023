package entity;

import java.util.*;

import entity.item.Item;
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

    // Method yang satu ini hanya digunakan saat inisiasi objek dari kelas House
    public void addObject(Furniture object, Point location) throws RoomException {
        int objectWidth, objectLength;
        if (object.getRotation()) {
            objectWidth = object.getLength();
            objectLength = object.getWidth();
        } else {
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

    private void addObject(Furniture object) throws RoomException {
        int x,y;
        System.out.printf("\nMasukkan x: ");
        x = scanner.nextInt();
        System.out.printf("\nMasukkan y: ");
        y = scanner.nextInt();

        Point location = new Point(x, y);

        int objectWidth, objectLength;
        if (object.getRotation()) {
            objectWidth = object.getLength();
            objectLength = object.getWidth();
        } else {
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

    private void removeObject(Furniture object) {
        Point location = object.getPoint();
        int objectWidth, objectLength;
    
        if (object.getRotation()) {
            objectWidth = object.getLength();
            objectLength = object.getWidth();
        } else {
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

    public void editRoom(Sim sim) {
        printRoom();
        System.out.printf("\nPilih aksi:\n1. Letakkan Objek\n2. Pindahkan Objek\n3. Ambil Objek\nNomor aksi:");
        int x = scanner.nextInt();
        while ((x < 1) || (x > 3)) {
            System.out.printf("\n\nSilakan ulangi input: ");
            x = scanner.nextInt();
        }
    
        // Memilih aksi
        switch (x) {
            case 1:
                // Menambahkan objek
                addObjectAction(sim);
                break;
            case 2:
                // Memindahkan objek
                moveObjectAction(sim);
                break;
            case 3:
                // Menghapus objek
                removeObjectAction(sim);
                break;
        }
    }

    private void addObjectAction(Sim sim) {
        Furniture currentFurniture = sim.getInventory().chooseFurniture();
        if (currentFurniture != null) {
            try {
                addObject(currentFurniture);
            } catch (RoomException e) {
                System.out.println("Penempatan furnitur gagal: " + e.getMessage());
                sim.getInventory().addItem((Item) currentFurniture);
                System.out.println("Furnitur telah diletakkan kembali ke inventory sim");
            }
        }
    }

    private void moveObjectAction(Sim sim) {
        if (objects.isEmpty()) {
            System.out.println("\nRuang ini tidak mempunyai furnitur");
            return;
        }
        Furniture currentFurniture = null;
        int idx = -1;
        int count = 0;
        String furnitureName = null;

        while (count == 0){
            System.out.printf("\nMasukkan nama furnitur: ");
            furnitureName = scanner.nextLine();
            for (Furniture furniture : objects){
                if (furniture.getName().equals(furnitureName)){
                    idx = objects.indexOf(furniture);
                    count++;
                }
            }
            if (count ==0 ){
                System.out.println("\nNama furnitur tidak valid");
            }
        }

        if (count == 1){
            currentFurniture = objects.get(idx);
            removeObject(objects.get(idx));
        } else {
            System.out.println("Terdapat " + count + " " + furnitureName + " di ruangan ini");
            count = 1;
            for (Furniture furniture : objects){
                if (furniture.getName().equals(furnitureName)){
                    System.out.println(furniture.getName() + " " + count + ": " + furniture.getPoint().displayPoint());
                }
            }
                
            int i, j;
            boolean found = false;
                
            while (!found){
                System.out.println("\nMasukkan koordinat furnitur yang ingin dipindahkan");
                System.out.printf("\nX: ");
                i = scanner.nextInt();
                System.out.printf("\nY: ");
                j = scanner.nextInt();
                for (Furniture furniture : objects){
                    if (furniture.getPoint().equals(i, j)){
                        found = true;
                        currentFurniture = furniture;
                        removeObject(furniture);
                        break;
                    }
                }
                if (!found){
                    System.out.println("\nKoordinat tidak valid");
                }
            }
        }

        try {
            this.addObject(currentFurniture);
        } catch (RoomException e) {
            System.out.println("Penempatan furnitur gagal: " + e.getMessage());
            sim.getInventory().addItem((Item) currentFurniture);
            System.out.println("Furnitur telah diletakkan kembali ke inventory sim");
        }
    }

    private void removeObjectAction(Sim sim) {
        if (objects.isEmpty()) {
            System.out.println("\nRuang ini tidak mempunyai furnitur");
            return;
        }
        Furniture currentFurniture = null;
        int idx = -1;
        int count = 0;
        String furnitureName = null;

        while (count == 0){
            System.out.printf("\nMasukkan nama furnitur: ");
            furnitureName = scanner.nextLine();
            for (Furniture furniture : objects){
                if (furniture.getName().equals(furnitureName)){
                    idx = objects.indexOf(furniture);
                    count++;
                }
            }
            if (count ==0 ){
                System.out.println("\nNama furnitur tidak valid");
            }
        }

        if (count == 1){
            currentFurniture = objects.get(idx);
            removeObject(objects.get(idx));
        } else {
            System.out.println("Terdapat " + count + " " + furnitureName + " di ruangan ini");
            count = 1;
            for (Furniture furniture : objects){
                if (furniture.getName().equals(furnitureName)){
                    System.out.println(furniture.getName() + " " + count + ": " + furniture.getPoint().displayPoint());
                }
            }
                
            int i, j;
            boolean found = false;

            while (!found){
                System.out.println("\nMasukkan koordinat furnitur yang ingin dimasukkan ke inventory");
                System.out.printf("\nX: ");
                i = scanner.nextInt();
                System.out.printf("\nY: ");
                j = scanner.nextInt();
                for (Furniture furniture : objects){    
                    if (furniture.getPoint().equals(i, j)){
                        found = true;
                        currentFurniture = furniture;
                        removeObject(furniture);
                        break;
                    }
                }
                    if (!found){
                        System.out.println("\nKoordinat tidak valid");
                    }
            }
        }
        sim.getInventory().addItem((Item) currentFurniture);
        System.out.println("Furnitur telah diletakkan kembali ke inventory sim");
    }

    public void printRoom() {
        System.out.println("\nTampilan ruangan:");
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

        System.out.println("\nDaftar furnitur");

        int count = 1;

        for (Furniture furniture : objects){
            System.out.printf("%d. %s", count, furniture.getName());
            count++;
        }

        if (count == 1){
            System.out.println("\nidak ada furnitur");
        }
    }
}
