package entity;

import java.util.*;

import entity.item.furniture.Clock;
import entity.item.furniture.TableAndChair;
import entity.item.furniture.Toilet;
import entity.item.furniture.bed.SingleBed;
import entity.item.furniture.stove.GasStove;
import entity.sim.Sim;

public class House {
    Sim owner;
    private Point location;
    private List<Room> rooms;
    Room primaryRoom;
    Scanner scanner = new Scanner(System.in);
    
    public House(Point location) {
        this.location = location;
        this.rooms = new ArrayList<>();
        primaryRoom = new Room("Ruang Utama", new Point(0, 0));
        try {
            primaryRoom.addObject(new SingleBed(1), new Point(0, 0));
            primaryRoom.addObject(new Toilet(1), new Point(5, 0));
            primaryRoom.addObject(new GasStove(1), new Point(5, 4));
            primaryRoom.addObject(new Clock(1), new Point(0, 5));
            primaryRoom.addObject(new TableAndChair(1), new Point(1, 0));
        } catch (RoomException e) {
            System.out.println(e.getMessage());
        }
        rooms.add(primaryRoom);
    }

    public static House getInstance(Point location){
        return new House(location);
    }
    
    public String getName() {
        return String.format(owner.getName() + "'s House");
    }

    public void setOwner(Sim sim){
        owner = sim;
    }

    public Sim getOwner(){
        return owner;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public void addRoom(Room room) {
        boolean valid = false;
        int x = room.getLocation().getX();
        int y = room.getLocation().getY();
        
        while (!valid){
            System.out.println("Pilih sebelah mana dari " + room.getName() + " yang ingin dibangun ruangan baru:\n1. Kanan\n2. Kiri\n3. Atas\n4. Bawah\n\nMasukkan 5 untuk membatalkan operasi");
            
            System.out.printf("Masukkan nomor: ");
            
            int direction = scanner.nextInt();

            while ((direction < 1) || (direction > 5)){
                System.out.printf("\n\nMasukan tidak valid\nMasukkan nomor: ");
                direction = scanner.nextInt();
            }
            switch (direction){
                case 1:
                    x++;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    y++;
                    break;
                case 4:
                    y--;
                    break;
                case 5:
                    return;
            }

            boolean collision = false;
            for (Room i : rooms){
                if (i.getLocation().equals(x, y)){
                    collision = true;
                }
            }

            if (collision){
                System.out.println("Tidak dapat membangun ruangan di sini karena sudah ada ruangan di tempatnya, silakan ulangi proses");
            } else{
                valid = true;
            }
        }

        Point roomLocation = new Point(x, y);
        String roomName = scanner.nextLine();

        Thread buildRoomThread = new Thread(() -> {
            System.out.println(this.getName() + " sedang di-upgrade");
                
            try {
                Thread.sleep(18 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            rooms.add(new Room(roomName, roomLocation));

            System.out.println(this.getName() + " selesai di-upgrade");
        });

        buildRoomThread.start();
    }

    public void displayRoom() {
        System.out.println("Daftar ruangan di " + this.getName() + ":");
        for (Room room : rooms) {
            System.out.println("> " + room.getName());
        }
    }

    public Room getRoom() {
        Room retRoom = null;
        boolean valid = false;
        
        displayRoom();

        while (!valid) {
            System.out.print("Masukkan nama ruangan: ");
            String roomName = scanner.nextLine();
            
            for (Room room : rooms) {
                if (room.getName().toLowerCase().equals(roomName.toLowerCase())) {
                    retRoom = room;
                    valid = true;
                    break;
                }
            }
            
            if (!valid) {
                System.out.println("\n\nNama ruangan tidak valid, silahkan ulangi masukkan\n");
            }
        }
        
        return retRoom;
    }
}
