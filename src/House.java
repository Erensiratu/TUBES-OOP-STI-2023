

import java.util.*;



public class House {
    Sim owner;
    private Point location;
    private List<Room> rooms;
    private boolean upgrading;
    Room primaryRoom;
    Scanner scanner = new Scanner(System.in);

    public synchronized boolean isUpgrading() {
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
        rooms.add(primaryRoom);
    }

    public static House getInstance(Point location){
        return new House(location);
    }
    
    public String getName() {
        return String.format("Rumah " + owner.getName());
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
        BuildRoomThread buildRoomThread = new BuildRoomThread(1000*18*60, roomNameFinal, roomLocation, this);
        buildRoomThread.start();
        Sim.getCurrentWorld().getClock().addSecEventListener(buildRoomThread);
        buildRoomThread = null;
        Sim.getCurrentWorld().getClock().addPassiveThread(buildRoomThread);
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


}
