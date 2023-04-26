package entity;
import java.util.*;
import entity.sim.Sim;
import entity.Timer;
import entity.item.Useable;
import entity.House;
import entity.Room;

public class SimPlicity {
    private Sim currentSim;
    private World currentWorld;
    Scanner scanner = new Scanner(System.in);

    private static SimPlicity instance = new SimPlicity();

    private SimPlicity(){
        currentWorld = World.getInstance();
    }

    public static SimPlicity getInstance(){
        return instance;
    }

    public void startGame(){
        currentWorld.getClock().setTime();
        addSim();
    }
    
    public void save(){
        //buat sim
    }

    public void load(){
        //buat sim
    }

    public void help(){
        //buat sim
    }

    public void exit(){
        System.exit(0);
    }

    public void addSim(){
        String simName = "";
        while (simName.isEmpty()) {
            System.out.printf("Masukkan nama sim: ");
            simName = scanner.nextLine().trim();
            if (simName.isEmpty()){
                System.out.printl("\n\nSilakan masukkan nama yang benar\n");
            }
        }

        Point point = null;
        boolean valid = false;
        while (!valid) {
            System.out.print("\n\nMasukkan koordinat rumah di axis x (0-63): ");
            int x = scanner.nextInt();
            System.out.print("\n\nMasukkan koordinat rumah di axis y (0-63): ");
            int y = scanner.nextInt();
            point = new Point(x, y);
            if (x < 0 || x > 63 || y < 0 || y > 63) {
                System.out.println("Kedua titik koordinat harus dalam range 0-63");
                continue;
            }
            valid = true;
            for (House house : currentWorld.getListHouse()) {
                if (house.getLocation().equals(point)) {
                    System.out.println("Sudah ada rumah di titik ini");
                    valid = false;
                    break;
                }
            }
        }

        currentWorld.addHouse(House.getInstance(point));

        currentWorld.addSim(Sim.getInstance(simName, ));
    }

    public void changeSim(){
        //buat sim
    }

    public void viewSimInfo(){
        //buat sim
    }

    public void action(){

    }

    public void viewCurrentLocation(){

    }

    public void moveRoom(){

    }

    public void listObject(){

    }

    public void goToObject(){

    }

    public void viewInventory(){

    }

    public void editRoom(){

    }

    public void upgradeHouse(){
    
    }
    public static void main(String[] args) throws Exception {
        /*ArrayList<Item> items = new ArrayList<>();
        items.add(new Bistik());
        items.add(new TumisSayur());
        System.out.println(items.get(0).getName());
        System.out.println(items.get(1).getName());*/
        Scanner input = new Scanner(System.in);
        String userInputsString = input.nextLine();
        int userInputint;
        World currentWorld;
        Sim currentSim;
        House currentHouse;
        Room currentRoom;
        // inisialisasi
        if (userInput.equals("StartGame")){
            World.init();
            userInputsString = input.nextLine();
            /*
                Inisialisasi SIM
             */
        }
        // inisialisasi selesai
        boolean run = true;
        while (run){
            currentWorld.getClock().setTime();
            if (input.hasNextLine()){
                userInputsString = input.nextLine();
                if (userInput.equals("AddSim")){
                    userInputsString = input.nextLine();
                    /*
                     Konstruktor Sim
                     */
                }
                else if (userInputsString.equals("ChangeSim")){
                    userInputint = input.nextInt();
                    if (userInputint < currentWorld.getListSim().size()){
                        currentSim = currentWorld.getListSim().get(userInputint);
                    }
                    input.nextLine();
                }
                else if (userInputsString.equals("ViewSimInfo")){
                    System.out.println("Health = " +currentSim.getStatus().getHealth());
                    System.out.println( "Hunger = " + currentSim.getStatus().getHunger());
                    System.out.println( "Money = " + currentSim.getStatus().getMoney());
                    System.out.println( "Mood = " + currentSim.getStatus().getMood());
                }
                else if (userInputsString.equals("Action")){
                    userInputsString = input.nextLine();
                    if (userInputsString.equals("Exercise")){
                        userInputint = input.nextInt();
                        input.nextLine();
                        currentSim.getAction().exercise(userInputint);
                    }
                    else if (userInputsString.equals("UseItem")){
                        if (currentSim.getItem() instanceof Useable){
                            currentSim.useItem();
                        }
                        
                    }  
                    else if (userInputsString.equals("Transfer")){
                        System.out.println("Masukkan ID penerima");
                        userInputint = input.nextInt();
                        input.nextLine();
                        if (userInputint < currentWorld.getListSim().size()){
                            int i = userInputint;
                            System.out.println("Masukkan jumlah uang yang akan ditransfer");
                            userInputint = input.nextInt();
                            input.nextLine();   
                            if (userInputint <= currentSim.getStatus().getMoney()){
                                currentSim.getAction().transferMoney(userInputint, currentWorld.getListSim().get(i));
                            }
                        }
                    }
                    else if (userInputsString.equals("DayDream")){
                        userInputint = input.nextInt();
                        input.nextLine();
                        currentSim.getAction().dayDream(userInputint);
                    }
                    else if (userInputsString.equals("Work")){
                        currentSim.getAction().work();
                    }
                }
            }
        }
    }
}
