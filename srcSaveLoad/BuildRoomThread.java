

public class BuildRoomThread extends PassiveThread {
    private String roomNameFinal;
    private Point roomLocation;
    private House house;
    public BuildRoomThread(long duration, String roomNameFinal, Point roomLocation, House house){
        setDuration(duration);;
        this.roomNameFinal = roomNameFinal;
        this.roomLocation = roomLocation;
        this.house = house;
        setName("Pembangunan ruangan "+roomNameFinal+" di dalam "+house.getName());
    }
    public void run(){
        house.setUpgrading(true);
        System.out.println(roomNameFinal + " sedang di-upgrade\n");
        while(getDuration() > 0){

        }
        house.getRoomList().add(new Room(roomNameFinal, roomLocation));
        System.out.println(roomNameFinal + " selesai di-upgrade\n");
        house.setUpgrading(false);
    }
    public void changeSecUpdate(){
        durationDecrement();

    }

    public int getType(){
        return 1;
    }

    public String getArg(){
        return roomNameFinal + " " + roomLocation.getX() + " " + roomLocation.getY() + " " + house.getName();
    }
}