package entity;

import java.util.ArrayList;
import java.util.List;


public class Timer {
    private long time;
    private long start = System.currentTimeMillis();
    private int day;
    private static Timer instance;
    private static ArrayList<ChangeDayListener> subscriber;
    private static ArrayList<Sim> listSim;

    private Timer(ArrayList<Sim> listSim, SimPlicity s){
        ArrayList<ChangeDayListener> subscriber = new ArrayList<ChangeDayListener>();
        time = 0;
        day = 0;
        Timer.listSim = listSim;
        subscriber.add(s);
        for (ChangeDayListener i : listSim){
            subscriber.add(i);
        }
        
    }
    public static Timer getInstance(){
        return instance;
    }

    private Timer(ArrayList<Sim> listSim,int day, long time, SimPlicity s){
        ArrayList<ChangeDayListener> subscriber = new ArrayList<ChangeDayListener>();
        this.time = time;
        this.day = day;
        Timer.listSim = listSim;
        subscriber.add(s);
        for (ChangeDayListener i : listSim){
            subscriber.add(i);
        }
    }

    public synchronized static void init(ArrayList<Sim> listSim, SimPlicity s){
        if (instance == null){
            instance = new Timer(listSim, s);
        }
    }
    
    public synchronized static void init(ArrayList<Sim> listSim, int day, long time, SimPlicity s){
        if (instance == null){
            instance = new Timer(listSim, day, time, s);
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
            if (day != (int) (time/720000)){
                day = (int) (time/720000);
                for(ChangeDayListener i : subscriber){
                    i.changeDayUpdate();
                }
            }

            
            
        }
    }
    public long getTime(){
        return time;
    }
    public int getDay(){
        return day;
    }
    public long getRemainingTime(){
        return 720000-(time % 720000);
    }

}