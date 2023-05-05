


import java.util.Currency;



public class Sim implements ChangeDayListener, TickListener {
    String name;
    Occupation occupation;
    InventoryManager inventory;
    Action action;
    Point currentLocation;
    Room currentRoom;
    House currentHouse;
    static World  currentWorld;
    Item currentItem;
    Status status;
    long timeSinceLastSupper;
    long timeSinceLastSleep;
    boolean hadShit;


    public Sim(String name, World currentWorld, House currentHouse, Room currentRoom, Point currentLocation){
        this.name = name;
        Sim.currentWorld = currentWorld;
        this.currentHouse = currentHouse;
        this.currentRoom = currentRoom;
        this.currentLocation = currentLocation;
        occupation = new Occupation(this);
        action = new Action(this);
        inventory = new InventoryManager(this);
        status = new Status();
        currentHouse.setOwner(this);
        timeSinceLastSleep = currentWorld.getClock().getTime();
        timeSinceLastSupper = currentWorld.getClock().getTime();

        hadShit = true;
        Sim.getCurrentWorld().getClock().addSecEventListener(this);
    }

    public static Sim getInstance(String name, World currentWorld, House currentHouse, Room currentRoom, Point currentLocation){
        return new Sim(name, currentWorld, currentHouse, currentRoom, currentLocation);
    }


    //method
    public String getName(){
        return name;
    }
    
    public Point getLocation(){
        return currentLocation;
    }

    public Room getRoom(){
        return currentRoom;
    }

    public World getWorld(){
        return currentWorld;
    }
    
    public House getHouse(){
        return currentHouse;
    }

    public void setLocation(Point newLocation){
        currentLocation = newLocation;
    }

    public void setRoom(Room newRoom){
        currentRoom = newRoom;
    }

    public void setHouse(House newHouse){
        currentHouse = newHouse;
    }

    public void setItem(Item newItem){
        this.currentItem = newItem;
    }

    public Item getItem(){
        return currentItem;
    }

    public Occupation getOcupation(){
        return occupation;
    }

    public InventoryManager getInventory(){
        return inventory;
    }

    public Status getStatus(){
        return status;
    }

    public Action getAction(){
        return action;
    }

    public long getTimeSinceLastSupper(){
        return timeSinceLastSupper;
    }
    public synchronized void setTimeSinceLastSupper(long timeSinceLastSupper){
        this.timeSinceLastSupper = timeSinceLastSupper;
    }
    public long getTimeSinceLastSleep(){
        return timeSinceLastSleep;
    }
    public void setTimeSinceLastSleep(long timeSinceLastSleep){
        this.timeSinceLastSleep = timeSinceLastSleep;
    }
    public boolean getHadShit(){
        return hadShit;
    }
    public synchronized void setHadShit(boolean hadShit){
        this.hadShit = hadShit;
    }


    public static World getCurrentWorld(){
        return currentWorld;
    }

    public Sim searchSim(String simName){
        for (Sim sim : currentWorld.getListSim()){
            if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                return sim;
            }
        }
        return null;
    }

    public void changeSecUpdate(){
        setTimeSinceLastSleep(getTimeSinceLastSleep()+1000);
        setTimeSinceLastSupper(getTimeSinceLastSupper()+1000);
        if (getTimeSinceLastSleep() >= 600000){
            setTimeSinceLastSleep(0);
            status.decreaseMood(5);
            status.decreaseHealth(5);
            System.out.println("WARNING!!\n"+getName()+" kekurangan tidur!\n Kesehatan dan kesejahteraan berkurang 5.");
        }
        if ((getTimeSinceLastSupper() >= 240000) && (!getHadShit())){
            status.decreaseMood(5);
            status.decreaseHealth(5);  
            setTimeSinceLastSupper(0);
            System.out.println("WARNING!!\n"+getName()+" belum buang air!\n Kesehatan dan kesejahteraan berkurang 5.");
        }

    }

    @Deprecated
    public void changeDayUpdate(){
        
    }

    public boolean isUsed(){
        return status.isAlive();
    }


    public void moveHouse(House house){
        if (!action.isIdle()){
            System.out.println("Maaf, Sim sedang sibuk.");
        }

        long duration = (currentHouse.getLocation().getDistance(house.getLocation()))*1000;
        Thread moveThread = new Thread( () -> {
            action.setIdle(false);
            System.out.println(name + " sedang berjalan ke ke "+ house.getName() + "\n");
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentHouse = house;
            currentRoom = currentHouse.getPrimaryRoom();
            status.addMood(duration/30);
            status.decreaseHunger(duration/30);
            System.out.print("Kunjungan "+ name + " selesai\n");
            action.setIdle(true);
        });
        moveThread.start();
        try {
            moveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
