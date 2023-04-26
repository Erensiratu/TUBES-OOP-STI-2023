package entity;
import java.util.*;
import entity.sim.Sim;
import entity.Timer;
import entity.item.Item;
import entity.item.Useable;
import entity.item.furniture.Furniture;
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
        currentSim = currentWorld.getListSim().get(0);
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
                System.out.println("\n\nSilahkan masukkan nama yang benar\n");
            }
            for (Sim sim : currentWorld.getListSim()){
                if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                    System.out.println(sim.getName() + " telah diambil, silahkan pilih nama yang lain\n");
                    continue;
                }
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
        
        House tempHouse = House.getInstance(point);
        Sim tempSim = Sim.getInstance(simName, currentWorld, tempHouse, tempHouse.getPrimaryRoom(), Point.getInstance(0, 0));
        tempSim.setItem(tempSim.getRoom().getObject(Point.getInstance(0, 0)));

        currentWorld.addHouse(tempHouse);
        currentWorld.addSim(tempSim);
    }

    public void changeSim(){
        if (currentWorld.getListSim().size() > 1){
            String simName = "";
            boolean found = false;
            while (!found || simName.isEmpty() || simName.toLowerCase(null).equals(currentSim.getName().toLowerCase())){
                System.out.print("\n\nMasukkan nama sim: ");
                simName = scanner.nextLine().trim();
                if (simName.isEmpty()) {
                    System.out.println("Masukkan nama sim yang valid");
                    continue;
                }
                if (simName.toLowerCase(null).equals(currentSim.getName().toLowerCase())){
                    System.out.println(simName + " sedang dimainkan\n");
                    continue;
                }
                for (Sim sim : currentWorld.getListSim()) {
                    if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                        System.out.println(sim.getName() + " menjadi sim yang dimainkan\n");
                        currentSim = sim;
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("Tidak ada sim dengan nama " + simName);
                }
            }
        } else {
            System.out.println("Tidak ada sim lain di dunia ini");
        }
    }

    public void viewSimInfo(){
        currentSim.getStatus().displayStatus();
    }

    public void action(){
        currentSim.getAction().startActivity();
    }

    public void viewCurrentLocation(){
        System.out.println("Lokasi sim di ruangan:\nX: " + currentSim.getLocation().getX() + "\nY: " + currentSim.getLocation().getY());
        if (currentSim.getItem() == null){
            System.out.println("Sim sedang berada di objek manapun");
        } else {
            System.out.println("Sim sedang berada di objek " + currentSim.getItem().getName());
        }
    }

    public void moveRoom(){
        if (!currentSim.getAction().isIdle()){
            System.out.println("Sim sedang sibuk");
            return;
        }
        if (currentSim.getHouse().getRoomList().size() == 1){
            System.out.println("Hanya ada satu ruangan di rumah ini");
            return;
        }

        boolean valid = false;  
        currentSim.getHouse().displayRoom();

        while (!valid) {
            System.out.print("Masukkan nama ruangan: ");
            String roomName = scanner.nextLine().trim();

            if (roomName.toLowerCase(null).equals(currentSim.getRoom().getName().toLowerCase())){
                System.out.println(currentSim.getName() + " sudah berada di " + currentSim.getRoom().getName());
                continue;
            }
                
            for (Room room : currentSim.getHouse().getRoomList()) {
                if (room.getName().toLowerCase().equals(roomName.toLowerCase())) {
                    currentSim.setRoom(room);
                    valid = true;
                    break;
                }
            }
                
            if (!valid) {
                System.out.println("\n\nNama ruangan tidak valid, silahkan ulangi masukkan\n");
            }
        }
    }

    public void listObject(){
        currentSim.getRoom().printRoom();
    }

    public void goToObject(){
        currentSim.getRoom().printRoom();

        if (currentSim.getRoom().getObjectList().size() == 0){
            System.out.println("\nTidak ada furnitur di ruangan ini");
            return;
        }

        ArrayList<Furniture> objects = currentSim.getRoom().getObjectList();
        Furniture newFurniture = null;
        int idx = -1;
        int count = 0;
        String furnitureName = null;

        while (count == 0){
            System.out.printf("\nMasukkan nama furnitur: ");
            furnitureName = scanner.nextLine().trim();
            for (Furniture furniture : objects){
                if (furniture.getName().toLowerCase().equals(furnitureName.toLowerCase())){
                    idx = objects.indexOf(furniture);
                    count++;
                }
            }
            if (count == 0 ){
                System.out.println("\nNama furnitur tidak valid");
            }
        }

        if (count == 1){
            newFurniture = objects.get(idx);
        } else {
            System.out.println("Terdapat " + count + " " + furnitureName + " di ruangan ini");
            count = 1;
            for (Furniture furniture : objects){
                if (furniture.getName().equals(furnitureName)){
                    System.out.println(furniture.getName() + " " + count + ": " + furniture.getPoint().displayPoint());
                }
            }
                
            int x, y;
            boolean found = false;

            while (!found){
                System.out.println("\nMasukkan koordinat furnitur yang ingin dikunjungi");
                System.out.printf("\nX: ");
                x = scanner.nextInt();
                System.out.printf("\nY: ");
                y = scanner.nextInt();
                for (Furniture furniture : objects){    
                    if (furniture.getPoint().equals(x, y)){
                        found = true;
                        newFurniture = furniture;
                        break;
                    }
                }
                if (!found){
                    System.out.println("\nKoordinat tidak valid");
                }
            }
        }

        currentSim.setItem(newFurniture);
        currentSim.setLocation(newFurniture.getPoint());
    }

    public void viewInventory(){
        currentSim.getInventory().displayInventory();
    }

    public void editRoom(){
        if (!currentSim.getHouse().getOwner().getName().equals(currentSim.getName())){
            System.out.println(currentSim.getName() + " sedang tidak berada di rumahnya");
            return;
        }
        currentSim.getRoom().editRoom(currentSim);
    }

    public void upgradeHouse(){
        if (!currentSim.getHouse().getOwner().getName().equals(currentSim.getName())){
            System.out.println(currentSim.getName() + " sedang tidak berada di rumahnya");
            return;
        }
        
        if (currentSim.getHouse().getRoomList().size() == 1){
            currentSim.getHouse().addRoom(currentSim.getHouse().getPrimaryRoom());
        } else{
            currentSim.getHouse().displayRoom();
            boolean valid = false;  

            while (!valid) {
                System.out.print("Masukkan nama ruangan yang dijadikan acuan: ");
                String roomName = scanner.nextLine().trim();
                    
                for (Room room : currentSim.getHouse().getRoomList()) {
                    if (room.getName().toLowerCase().equals(roomName.toLowerCase())) {
                        currentSim.getHouse().addRoom(room);
                        valid = true;
                        break;
                    }
                }
                    
                if (!valid) {
                    System.out.println("\n\nNama ruangan tidak valid, silahkan ulangi masukkan\n");
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        /*ArrayList<Item> items = new ArrayList<>();
        items.add(new Bistik());
        items.add(new TumisSayur());
        System.out.println(items.get(0).getName());
        System.out.println(items.get(1).getName());*/
    //     Scanner input = new Scanner(System.in);
    //     String userInputsString = input.nextLine();
    //     int userInputint;
    //     World currentWorld;
    //     Sim currentSim;
    //     House currentHouse;
    //     Room currentRoom;
    //     // inisialisasi
    //     if (userInput.equals("StartGame")){
    //         World.init();
    //         userInputsString = input.nextLine();
    //         /*
    //             Inisialisasi SIM
    //          */
    //     }
    //     // inisialisasi selesai
    //     boolean run = true;
    //     while (run){
    //         currentWorld.getClock().setTime();
    //         if (input.hasNextLine()){
    //             userInputsString = input.nextLine();
    //             if (userInput.equals("AddSim")){
    //                 userInputsString = input.nextLine();
    //                 /*
    //                  Konstruktor Sim
    //                  */
    //             }
    //             else if (userInputsString.equals("ChangeSim")){
    //                 userInputint = input.nextInt();
    //                 if (userInputint < currentWorld.getListSim().size()){
    //                     currentSim = currentWorld.getListSim().get(userInputint);
    //                 }
    //                 input.nextLine();
    //             }
    //             else if (userInputsString.equals("ViewSimInfo")){
    //                 System.out.println("Health = " +currentSim.getStatus().getHealth());
    //                 System.out.println( "Hunger = " + currentSim.getStatus().getHunger());
    //                 System.out.println( "Money = " + currentSim.getStatus().getMoney());
    //                 System.out.println( "Mood = " + currentSim.getStatus().getMood());
    //             }
    //             else if (userInputsString.equals("Action")){
    //                 userInputsString = input.nextLine();
    //                 if (userInputsString.equals("Exercise")){
    //                     userInputint = input.nextInt();
    //                     input.nextLine();
    //                     currentSim.getAction().exercise(userInputint);
    //                 }
    //                 else if (userInputsString.equals("UseItem")){
    //                     if (currentSim.getItem() instanceof Useable){
    //                         currentSim.useItem();
    //                     }
                        
    //                 }  
    //                 else if (userInputsString.equals("Transfer")){
    //                     System.out.println("Masukkan ID penerima");
    //                     userInputint = input.nextInt();
    //                     input.nextLine();
    //                     if (userInputint < currentWorld.getListSim().size()){
    //                         int i = userInputint;
    //                         System.out.println("Masukkan jumlah uang yang akan ditransfer");
    //                         userInputint = input.nextInt();
    //                         input.nextLine();   
    //                         if (userInputint <= currentSim.getStatus().getMoney()){
    //                             currentSim.getAction().transferMoney(userInputint, currentWorld.getListSim().get(i));
    //                         }
    //                     }
    //                 }
    //                 else if (userInputsString.equals("DayDream")){
    //                     userInputint = input.nextInt();
    //                     input.nextLine();
    //                     currentSim.getAction().dayDream(userInputint);
    //                 }
    //                 else if (userInputsString.equals("Work")){
    //                     currentSim.getAction().work();
    //                 }
    //             }
    //         }
    //     }
    }
}
