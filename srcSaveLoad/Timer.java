

import java.util.ArrayList;
import java.util.List;


public class Timer {
    private long time;
    private int day;
    private boolean run = false;
    private boolean exit = false;
    private static Timer instance;
    private static ArrayList<ChangeDayListener> subscriber = new ArrayList<ChangeDayListener>(); ;
    private static ArrayList<Sim> listSim;
    private static ArrayList<TickListener> secondSubscriber = new ArrayList<TickListener>(); 
    private static ArrayList<PassiveThread> listPassiveThread = new ArrayList<PassiveThread>();

    public static ArrayList<PassiveThread> getListPassiveThread() {
        return listPassiveThread;
    }
    private Timer(ArrayList<Sim> listSim){

        time = 0;
        day = 0;
        Timer.listSim = listSim;
        for (ChangeDayListener i : listSim){
            subscriber.add(i);
        }
        
    }
    public static Timer getInstance(){
        return instance;
    }

    private Timer(ArrayList<Sim> listSim,int day, long time){
        this.time = time;
        this.day = day;
        Timer.listSim = listSim;
        for (ChangeDayListener i : listSim){
            subscriber.add(i);
        }
    }

    public synchronized static void init(ArrayList<Sim> listSim){
        if (instance == null){
            instance = new Timer(listSim);
        }
    }
    
    public synchronized static void init(ArrayList<Sim> listSim, int day, long time){
        if (instance == null){
            instance = new Timer(listSim, day, time);
        }
    }



    private void changeDay(){
        for(ChangeDayListener i : subscriber){
            i.changeDayUpdate();
        }
        System.out.println("Hari telah berubah, semoga hari besok lebih baik dari hari ini.");
        garbageDayCleaner();
    }

    private void changeSecond() {
        for(TickListener i : secondSubscriber){
            i.changeSecUpdate();
        }
        garbageSecondCleaner();
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

    public void checkRun(){
        boolean allIdle = true;
        for(Sim i : listSim){
            if (!(i.getAction().isIdle())){
                allIdle = false;
            }
        }
        run = !allIdle;
    }

    public Thread timerThread = new Thread(new Runnable(){

        public void run(){
            while(!exit){

                try {
                    Thread.sleep(1000);
                    
                } catch(InterruptedException e){

                }
                if (run){
                    time += 1000;
                    changeSecond();
                    if (day != (time/720000)){
                        day = (int) time/720000;
                        changeDay();
                    }
                }                
                checkRun();
            }
        }
    } ); 
    
    public void startTime(){
        timerThread.start();
    }

    public void addEventListener(ChangeDayListener i){
        subscriber.add(i);
    }

    public void addSecEventListener(TickListener i){
        secondSubscriber.add(i);
    }

    public void addPassiveThread(PassiveThread i){
        listPassiveThread.add(i);
    }
    public ArrayList<PassiveThread> getPassiveThread(){
        return listPassiveThread;
    }

    public void garbageSecondCleaner(){
        secondSubscriber.removeIf(e -> !e.isUsed());
        listPassiveThread.removeIf(e -> !e.isUsed());
    }
    public void garbageDayCleaner(){
        subscriber.removeIf(e->!e.isUsed());
    }
}