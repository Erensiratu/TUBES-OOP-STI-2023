package entity.sim;

import entity.item.Item;
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
    Status status;

    public Sim(String name, Room currentRoom, Point currentLocation, House currentHouse, Status status){
        this.name = name;
        this.currentRoom = currentRoom;
        this.currentLocation = currentLocation;
        alive = false;
        occupation = new Occupation();
        action = new Action(this);
        this.currentHouse = currentHouse;
        this.status = status;
    }


    //method
    public String getName(){
        return name;
    }

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

    public Status getStatus(){
        return status;
    }

    public Action getAction(){
        return action;
    }
}
