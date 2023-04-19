package entity.sim;

import entity.item.Item;
import entity.Action;
import entity.Point;
import entity.Room;
import entity.House;

import java.util.*;

public class Sim {
    String name;
    boolean alive;
    Occupation occupation;
    List<Item> inventory;
    Action action;
    Point currentLocation;
    Room currentRoom;
    House currentHouse;
    Item currentItem;

    public Sim(String name, Room currentRoom, Point currentLocation, House currentHouse){
        this.name = name;
        this.currentRoom = currentRoom;
        this.currentLocation = currentLocation;
        alive = false;
        occupation = new Occupation();
        this.currentHouse = currentHouse;
    }

    //method
    public void setAlive(){
        alive = true;
    }

    public boolean isAlive(){
        return alive;
    }

    public void moveLocation(Point newLocation){
        currentLocation = newLocation;
    }

    public void moveRoom(Room newRoom){
        currentRoom = newRoom;
    }

    public void moveHouse(House newHouse){
        currentHouse = newHouse;
    }

    public List<Item> getInventory(){
        return inventory;
    }
}
