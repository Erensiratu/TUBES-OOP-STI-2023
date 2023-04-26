import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import entity.sim.Sim;

public class World {
    final int WORLD_LENGTH = 64;
    final int WORLD_WIDTH = 64;
    private ArrayList<House> listHouse;
    private ArrayList<Sim> listSim;
    private Timer clock;

    private static World instance ;

    private World(){
        
        listHouse = new ArrayList<House>();
        listSim = new ArrayList<Sim>();
        Timer.init(listSim);
        clock = Timer.getInstance();
    }
    private World(ArrayList<House> listHouse, ArrayList<Sim> listSim, int day, long time){
        this.listHouse = listHouse;
        this.listSim = listSim;
        Timer.init( listSim ,day,time);
        clock = Timer.getInstance();
    }
    public static World getInstance(){
        return instance;
    }
    public synchronized static void init(){
        if (instance == null){
            instance = new World();
        }
    }
    public synchronized static void init(Timer clock, ArrayList<House> listHouse, ArrayList<Sim> listSim){
        if (instance == null){
            instance = new World(clock,listHouse,listSim);
        }
    }
    public synchronized void addHouse(House h){
        listHouse.add(h);
    }
    public synchronized void addSim(Sim s){
        listSim.add(s);
    }
    public ArrayList<House> getListHouse(){
        return listHouse;
    }
    public ArrayList<Sim> getListSim(){
        return listSim;
    }
    public Timer getClock(){
        return clock;
    }
}
