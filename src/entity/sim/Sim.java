package entity.sim;

import entity.item.Item;
import entity.Point;
import entity.Room;
import entity.World;
import entity.House;


public class Sim {
    String name;
    Occupation occupation;
    InventoryManager inventory;
    Action action;
    Point currentLocation;
    Room currentRoom;
    House currentHouse;
    World currentWorld;
    Item currentItem;
    Status status;

    public Sim(String name, World currentWorld, House currentHouse, Room currentRoom, Point currentLocation){
        this.name = name;
        this.currentWorld = currentWorld;
        this.currentHouse = currentHouse;
        this.currentRoom = currentRoom;
        this.currentLocation = currentLocation;
        occupation = new Occupation(this);
        action = new Action(this);
        inventory = new InventoryManager(this);
        status = new Status();
        currentHouse.setOwner(this);
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

    public Sim searchSim(String simName){
        for (Sim sim : currentWorld.getListSim()){
            if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                return sim;
            }
        }
        return null;
    }
}
