import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import entity.sim.Sim;

public class World {
    final int WORLD_LENGTH = 64;
    final int WORLD_WIDTH = 64;
    private ArrayList<House> listHouse ;
    private ArrayList<Sim> listSim;
    private Timer clock;

<<<<<<< HEAD
    private static World instance = new World();
    private ArrayList<House> listHouse ;
    private ArrayList<Sim> listSim;
    private Timer clock;

    private World(){}
    private static World instance ;

    private World(){
        Timer.init();
        clock = Timer.getInstance();
        listHouse = new ArrayList<House>();
        listSim = new ArrayList<Sim>();
    }
    private World(Timer clock, ArrayList<House> listHouse, private ArrayList<Sim> listSim){
        this.clock = clock;
        this.listHouse = listHouse;
        this.listSim = listSim;
=======
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
>>>>>>> 82922990dd280d41c49f5f660fa7afa57975b401
    }
    public static World getInstance(){
        return instance;
    }
<<<<<<< HEAD


=======
>>>>>>> 82922990dd280d41c49f5f660fa7afa57975b401
    public synchronized static void init(){
        if (instance == null){
            instance = new World();
        }
    }
<<<<<<< HEAD
    public synchronized static void init(Timer clock, ArrayList<House> listHouse, private ArrayList<Sim> listSim){
=======
    public synchronized static void init(Timer clock, ArrayList<House> listHouse, ArrayList<Sim> listSim){
>>>>>>> 82922990dd280d41c49f5f660fa7afa57975b401
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
<<<<<<< HEAD
    public int getTime(){
        return clock.getTime();
    }
}
=======
    public Timer getClock(){
        return clock;
    }
}
>>>>>>> 82922990dd280d41c49f5f660fa7afa57975b401
