package entity;

import java.util.*;



public class House {
    Sim owner;
    private Point location;
    private List<Room> rooms;
    private boolean upgrading;
    Room primaryRoom;
    Scanner scanner = new Scanner(System.in);

    public boolean isUpgrading() {
        return upgrading;
    }

    public void setUpgrading(boolean upgrading) {
        this.upgrading = upgrading;
    }
    
    public House(Point location) {
        upgrading = false;
        this.location = location;
        this.rooms = new ArrayList<>();
        primaryRoom = new Room("Ruang Utama", new Point(0, 0));
        try {
            primaryRoom.addObject(new SingleBed(1), new Point(0, 0));
            primaryRoom.addObject(new Toilet(1), new Point(5, 0));
            primaryRoom.addObject(new GasStove(1), new Point(4, 5));
            primaryRoom.addObject(new Clock(1), new Point(3, 0));
            primaryRoom.addObject(new TableAndChair(1), new Point(1, 1));
            primaryRoom.addObject(new Toilet(1), new Point(4, 0));
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
        String roomName = "";
        
        while (roomName.isEmpty()){
            scanner.nextLine();
            System.out.print("\nMasukkan nama ruangan baru: ");
            roomName = scanner.nextLine().trim();
            
            if (roomName.isEmpty()){
                System.out.println("Tolong masukkan nama yang valid");
            }
        }

        final String roomNameFinal = roomName;
        BuildRoomThread buildRoomThread = new BuildRoomThread(1000*18*60, roomNameFinal, roomLocation);
        buildRoomThread.start();
        Sim.getCurrentWorld().getClock().addEventListener(buildRoomThread);
    }

    public void displayRoom() {

        for (Room room : rooms) {
            System.out.println("> " + room.getName());
        }
    }

    public Room getPrimaryRoom(){
        return primaryRoom;
    }

    public List<Room> getRoomList(){
        return rooms;
    }

    public class BuildRoomThread extends Thread implements TickListener {
        private long duration ;
        private String roomNameFinal;
        private Point roomLocation;
        public BuildRoomThread(long duration, String roomNameFinal, Point roomLocation){
            this.duration = duration;
            this.roomNameFinal = roomNameFinal;
            this.roomLocation = roomLocation;

        }
        public void run(){
            setUpgrading(true);
            System.out.println(roomNameFinal + " sedang di-upgrade\n");
            while(duration > 0){
            }
            rooms.add(new Room(roomNameFinal, roomLocation));
            System.out.println(roomNameFinal + " selesai di-upgrade\n");
            setUpgrading(false);
        }
        public void changeSecUpdate(){
            duration -= 1000;
        }
    }
}
