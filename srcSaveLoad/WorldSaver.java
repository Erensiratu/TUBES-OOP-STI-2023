import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class WorldSaver implements Serializable {
    private ArrayList<HouseSaver> listHouse = new ArrayList<HouseSaver>();
    private ArrayList<SimSaver> listSim = new ArrayList<SimSaver>();
    private long time;
    private int day;
    private ArrayList<PassiveThreadSaver> listPassiveThread = new ArrayList<PassiveThreadSaver>();

    public ArrayList<HouseSaver> getListHouse() {
        return listHouse;
    }

    public ArrayList<SimSaver> getListSim() {
        return listSim;
    }

    public long getTime() {
        return time;
    }

    public int getDay() {
        return day;
    }

    public ArrayList<PassiveThreadSaver> getListPassiveThread() {
        return listPassiveThread;
    }

    public WorldSaver(World w){
        time = World.getClock().getTime();
        day = World.getClock().getDay();
        for(House h : w.getListHouse()){
            listHouse.add(new HouseSaver(h));
        }
        for (Sim s : w.getListSim()){
            listSim.add(new SimSaver(s));
        }
        for(PassiveThread p: Timer.getListPassiveThread()){
            listPassiveThread.add(new PassiveThreadSaver(p));
        }
    }
}