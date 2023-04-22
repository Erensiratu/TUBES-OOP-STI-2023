package entity;

import java.util.ArrayList;

import entity.sim.Sim;

public class World {
    final int WORLD_LENGTH = 64;
    final int WORLD_WIDTH = 64;
    ArrayList<House> listHouse;
    ArrayList<Sim> listSim;
    private int time;
    private int dayPassed;

    private static World instance = new World();

    private World(){}

    public static World getInstance(){
        return instance;
    }


}
