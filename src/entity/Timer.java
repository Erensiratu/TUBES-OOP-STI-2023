package entity;

import java.util.ArrayList;
import java.util.List;

import entity.sim.Sim;

public class Timer {
    private long time;
    private long start = System.currentTimeMillis();
    private ArrayList<Sim> listSim;
    private int day;
    private static Timer instance;

    private Timer(ArrayList<Sim> listSim){
        this.listSim = listSim;
        time = 0;
        day = 0;
    }
    private Timer(ArrayList<Sim> listSim,int day, long time){
        this.time = time;
        this.day = day;
        this.listSim = listSim;
    }

    public synchronized static void init(ArrayList<Sim> listSim){
        if (instance == null){
            instance = new Timer(listSim);
        }
    }
    
    public synchronized static void Timer(ArrayList<Sim> listSim,int day, long time){
        if (instance == null){
            instance = new Timer(listSim, day, time);
        }
    }

    public synchronized void setTime(){
        start = System.currentTimeMillis();
    }

    public synchronized void updateTime(){
        boolean allIdle = true;
        for(Sim i : listSim){
            if (!(i.getAction().isIdle())){
                allIdle = false;
            }
        }
        if (!allIdle){
            time = time + System.currentTimeMillis() - start;
            day = (int) (time/720000);
        }
    }
    public long getTime(){
        return time;
    }
    public int getDay(){
        return day;
    }
}