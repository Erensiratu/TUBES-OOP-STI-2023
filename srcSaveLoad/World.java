
import java.time.Clock;
import java.util.ArrayList;

import javax.xml.crypto.KeySelector.Purpose;

import org.w3c.dom.ls.LSInput;


public class World {
    final int WORLD_LENGTH = 65;
    final int WORLD_WIDTH = 65;
    private static ArrayList<House> listHouse;
    private static ArrayList<Sim> listSim;
    private static Timer clock;

    private static World instance ;

    private World(){
        listHouse = new ArrayList<House>();
        listSim = new ArrayList<Sim>();
        Timer.init(listSim);
        clock = Timer.getInstance();
    }
    private World(ArrayList<House> listHouse, ArrayList<Sim> listSim, int day, long time){
        World.listHouse = listHouse;
        World.listSim = listSim;
        Timer.init( listSim ,day,time);
        clock = Timer.getInstance();
    }





    public synchronized static void init(WorldSaver w){
        if (instance == null){

            ArrayList<House> listHouse = new ArrayList<House>();
            ArrayList<Sim> listSim = new ArrayList<Sim>();

            instance = new World(listHouse,listSim, w.getDay(), w.getTime());

            for(SimSaver i : w.getListSim()){
                Sim j = new Sim(i);
                listSim.add(j);
            }
            for(HouseSaver i : w.getListHouse()){
                
                House j = new House(i);
                listHouse.add(j);
            }
            for(PassiveThreadSaver p : w.getListPassiveThread()){
                String[] args = p.getArg().split("\\s+");
                switch (p.getType()){
                    case 0:
                        Sim s = searchSim(args[0]);
                        int quantity = Integer.parseInt(args[2]);
                        Item item;
                        BuyItemThread buyThread;
                        switch(args[1]){
                            case "susu" :
                            case "ayam" :
                            case "bayam" :
                            case "kacang" :
                            case "kentang" :
                            case "nasi" :
                            case "sapi" :
                                // IngredientFactory.createIngredient(itemName, 0)
                                item = (Item) IngredientFactory.createIngredient(args[0], quantity);
                                break;
                            case "kasur single":
                            case "kasur queen":
                            case "kasur king":
                            case "kompor gas":
                            case "kompor listrik":
                            case "kanvas":
                            case "jam":
                            case "toilet":
                            case "shower":
                            case "kursi":
                            case "meja":
                            case "kursi dan meja":
                            case "meja dan kursi":
                                item = (Item) FurnitureFactory.createFurniture(args[0], quantity);
                                buyThread = new BuyItemThread(p.getDuration(), s, item );
                                buyThread.start();
                                clock.addSecEventListener(buyThread);
                                clock.addPassiveThread(buyThread);
                                break;
                            case "bistik":
                            case "nasi ayam":
                            case "nasi kari":
                            case "susu kacang":
                            case "tumis sayur":
                                item = (Item) CuisineFactory.createCuisine(args[0], quantity);
                                buyThread = new BuyItemThread(p.getDuration(), s, item );
                                buyThread.start();
                                clock.addSecEventListener(buyThread);
                                clock.addPassiveThread(buyThread);
                                break;
                            default :
                                System.out.print("LOAD ERROR");
                        }

                        break;  
                    case 1:
                        House h = searchHouse(args[3]);
                        Point loc = new Point(Integer.parseInt(args[1]),Integer.parseInt(args[2]) );
                        BuildRoomThread buildRoomThread = new BuildRoomThread(p.getDuration(), args[0], loc, h);
                        buildRoomThread.start();
                        clock.addSecEventListener(buildRoomThread);
                        clock.addPassiveThread(buildRoomThread);
                        break;
                    default:
                        System.out.print("LOAD ERROR");
                }
            }
        }
    }
    public static World getInstance(){
        return instance;
    }
    public synchronized static void init(){
        if (instance == null){
            instance = new World();
        }
    }
    public synchronized static void init(ArrayList<House> listHouse, ArrayList<Sim> listSim, int day, long time){
        if (instance == null){
            instance = new World(listHouse,listSim, day, time);
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
    public static Timer getClock(){
        return clock;
    }

    public static Sim searchSim(String simName){
        for (Sim sim : listSim){
            if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                return sim;
            }
        }
        return null;
    }

    
    public static House searchHouse(String houseName){
        for (House house : listHouse){
            if (house.getName().toLowerCase().equals(houseName.toLowerCase())) {
                return house;
            }
        }
        return null;
    }
}
