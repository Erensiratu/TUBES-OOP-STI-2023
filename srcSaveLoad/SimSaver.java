import java.io.Serializable;

public class SimSaver implements Serializable {
    private String name;
    private OccupationSaver occupation;
    private InventoryManagerSaver inventory;
    private Point currentLocation;
    private RoomSaver currentRoom; // TODO mestinya kelar
    private HouseSaver currentHouse; // TODO, mestinya kelar
    private StatusSaver status;
    private long timeSinceLastSupper;
    private long timeSinceLastSleep;
    private boolean hadShit;

    public String getName() {
        return name;
    }

    public OccupationSaver getOccupation() {
        return occupation;
    }

    public InventoryManagerSaver getInventory() {
        return inventory;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public RoomSaver getCurrentRoom() {
        return currentRoom;
    }

    public HouseSaver getCurrentHouse() {
        return currentHouse;
    }


    public StatusSaver getStatus() {
        return status;
    }

    public long getTimeSinceLastSupper() {
        return timeSinceLastSupper;
    }

    public long getTimeSinceLastSleep() {
        return timeSinceLastSleep;
    }

    public boolean isHadShit() {
        return hadShit;
    }

    public SimSaver(Sim sim){
        name = sim.getName();
        occupation = new OccupationSaver(sim.getOcupation());
        currentLocation = sim.getLocation();
        currentRoom = new RoomSaver( sim.getRoom());
        status = new StatusSaver( sim.getStatus());
        timeSinceLastSleep = sim.getTimeSinceLastSleep();
        timeSinceLastSupper = sim.getTimeSinceLastSupper();
        hadShit = sim.getHadShit();
        
    }
}