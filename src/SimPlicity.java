

import java.util.*;

public class SimPlicity implements ChangeDayListener{
    private Sim currentSim;
    private static World currentWorld;
    private static Scanner input = new Scanner(System.in);

    private static SimPlicity instance = new SimPlicity();
    private static boolean hasAddSim = false;

    private SimPlicity(){
        currentWorld = World.getInstance();
    }

    public static SimPlicity getInstance(){
        return instance;
    }

    public void checkGameOver(){
        boolean currentSimAlive = currentSim.getStatus().isAlive();
    
        Iterator<Sim> simIterator = currentWorld.getListSim().iterator();
        while (simIterator.hasNext()) {
            Sim sim = simIterator.next();
            if (!sim.getStatus().isAlive()) {
                Iterator<House> houseIterator = currentWorld.getListHouse().iterator();
                while (houseIterator.hasNext()) {
                    House house = houseIterator.next();
                    if (house.getOwner().equals(sim.getName())) {
                        houseIterator.remove();
                    }
                }
                simIterator.remove();
                System.out.println("\n" + sim.getName() + " telah wafat");
            }
        }
    
        if (currentWorld.getListSim().size() == 0){
            System.out.println("\nSemua sim telah wafat\n\nGAME OVER");
            exit();
        } else{
            if (!currentSimAlive){
                if (currentWorld.getListSim().size() == 1){
                    for (Sim sim : currentWorld.getListSim()) {
                        if (sim.getStatus().isAlive()) {
                            currentSim = sim;
                            System.out.println("\nSim yang tersisa hanya " + currentSim.getName() + "\n\nSim yang dimainkan sekarang adalah "  + currentSim.getName());
                            break;
                        }
                    }
                } else {
                    changeSim();
                }
            }
        }
    }

    public void startGame(){
        World.init();
        currentWorld = World.getInstance();
        currentWorld.getClock().addEventListener(this);
        
        addSim();
        currentSim = currentWorld.getListSim().get(0);
        currentWorld.getClock().startTime();
    }

    public void help(){
        System.out.println("                                     HELP                                     ");
        System.out.println("                               panduan permainan                              ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Selamat datang di SIMPLICITY, tempat tinggal manusia - manusia kecil yang akan");
        System.out.println("  bergantung pada bantuanmu! Bantu para sim untuk meraih kesejahteraan dengan ");
        System.out.println("      menjaga agar mereka tetap kenyang, senang, bugar, dan berkecukupan.     ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                              Instruksi Bermain                               ");
        System.out.println("                                                                              ");
        System.out.println("1.  Add Sim               : membuat sim baru");
        System.out.println("2.  Change Sim            : mengganti sim yang dikontrol");
        System.out.println("3.  View Sim Info         : menampilkan status sim");
        System.out.println("4.  Actions               : memilih aksi yang akan dilakukan oleh sim");
        System.out.println("5.  View Current Location : menampilkan lokasi sim terpilih");
        System.out.println("6.  Move Room             : memindahkan sim ke ruangan lain");
        System.out.println("7.  Object List           : menampilkan daftar objek yang berada di ruangan");
        System.out.println("8.  Go To Object          : memilih objek yang ingin dituju oleh sim");
        System.out.println("9.  View Inventory        : menampilkan isi inventory sim");
        System.out.println("10. Edit Room             : mengubah susunan objek di ruangan");
        System.out.println("11. Upgrade House         : menambahkan ruangan baru dalam rumah");
        System.out.println("12. Move House            : memindahkan sim ke rumah lain");
        System.out.println("13. Help                  : menampilkan panduan permainan");
        System.out.println("14. Exit                  : keluar dari permainan");
    }

    public void exit(){
        System.out.println("\nTerima kasih sudah bermain!!!");
        System.exit(0);
    }

    public void addSim(){ // 1
        // if (hasAddSim){
        //     System.out.println("\nHanya bisa menambahkan sim sekali tiap hari");
        //     return;
        // } else {
        //     hasAddSim = true;
        // }

        Scanner nameScanner = new Scanner(System.in);
        String simName = "";
        while (simName.isEmpty()) {
            System.out.printf("\nMasukkan nama sim: ");
            simName = nameScanner.nextLine().trim();
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
            System.out.print("\nMasukkan koordinat rumah di axis x (0-64): ");
            int x = input.nextInt();
            System.out.print("\nMasukkan koordinat rumah di axis y (0-64): ");
            int y = input.nextInt();
            point = new Point(x, y);
            if (x < 0 || x > 64 || y < 0 || y > 64) {
                System.out.println("Kedua titik koordinat harus dalam range 0-64");
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
        currentWorld.getClock().addEventListener(tempSim);
    }

    public void changeSim(){ // 2
        if (currentWorld.getListSim().size() > 1){
            System.out.println("\nDaftar sim lain di world ini: ");
            for (Sim otherSim : currentSim.getWorld().getListSim()){
                if (!otherSim.getName().equals(currentSim.getName())){
                    System.out.println("> " + otherSim.getName());
                }
            }
            String simName = "";
            boolean found = false;
            while (!found){
                System.out.print("\nMasukkan nama sim: ");
                simName = input.nextLine().trim();
                if (simName.isEmpty()) {
                    System.out.println("Masukkan nama sim yang valid");
                    continue;
                }
                else if (simName.toLowerCase().equals(currentSim.getName().toLowerCase())){
                    System.out.println("\n" + simName + " tidak bisa dipilih");
                    continue;
                }
                for (Sim sim : currentWorld.getListSim()) {
                    if (sim.getName().toLowerCase().equals(simName.toLowerCase())) {
                        currentSim = sim;
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("\nTidak ada sim dengan nama " + simName);
                }
            }

            System.out.println("\nSim yang dimainkan sekarang adalah " + currentSim.getName());
        } else {
            System.out.println("Tidak ada sim lain di dunia ini");
        }
    }

    public void viewSimInfo(){ // 3
        System.out.println("\nNama Sim    : " + currentSim.getName());
        System.out.println("Pekerjaan   : " + currentSim.getOcupation().getProfession().getName());
        currentSim.getStatus().displayStatus();
    }

    public void action(){ // 4
        currentSim.getAction().startActivity();
    }

    public void viewCurrentLocation(){ // 5
        System.out.println("\nLokasi sim di dunia: " + currentSim.getHouse().getName());
        System.out.println("\nLokasi sim di rumah: " + currentSim.getRoom().getName());
        System.out.println("\nLokasi sim di ruangan\nX: " + currentSim.getLocation().getX() + "\nY: " + currentSim.getLocation().getY());
        if (currentSim.getItem() == null){
            System.out.println("Sim sedang tidak berada di objek apapun, silahkan gunakan command Go To Object");
        } else {
            System.out.println("Sim sedang berada di objek " + currentSim.getItem().getName());
        }
    }

    public void moveRoom(){ // 6
        if (!currentSim.getAction().isIdle()){
            System.out.println("\nSim sedang sibuk");
            return;
        }
        if (currentSim.getHouse().getRoomList().size() == 1){
            System.out.println("\nHanya ada satu ruangan di rumah ini");
            return;
        }

        boolean valid = false;  
        System.out.println("\nBerikut daftar ruangan yang dimiliki.");
        currentSim.getHouse().displayRoom();

        while (!valid) {
            System.out.print("\nMasukkan nama ruangan: ");
            String roomName = input.nextLine().trim();

            if (roomName.toLowerCase().equals(currentSim.getRoom().getName().toLowerCase())){
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

    public void listObject(){ // 7
        currentSim.getRoom().printRoom();
    }

    public void goToObject(){ // 8
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
            furnitureName = input.nextLine().trim();
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
            ArrayList<Point> points = new ArrayList<>();
            System.out.println("Terdapat " + count + " " + furnitureName + " di ruangan ini");
            count = 1;
            int i = 0;
            for (Furniture furniture : objects){
                if (furniture.getName().toLowerCase().equals(furnitureName.toLowerCase())){
                    System.out.println(furniture.getName() + " " + (count+i) + ": " + furniture.getPoint().displayPoint());
                    i ++;
                    points.add(furniture.getPoint());
                }
            }
                
            int x, y;
            boolean found = false;

            while (!found){
                System.out.println("\nMasukkan koordinat furnitur yang ingin dikunjungi");
                System.out.printf("\nX: ");
                x = input.nextInt();
                System.out.printf("\nY: ");
                y = input.nextInt();

                for (Point p : points){
                    if (p.equals(x, y)){
                        found = true;
                    }
                }

                if (!found){
                    System.out.println("\nKoordinat tidak valid");
                    continue;
                }

                for (Furniture furniture : objects){    
                    if (furniture.getPoint().equals(x, y)){
                        found = true;
                        newFurniture = furniture;
                        break;
                    }
                }
            }
        }

        currentSim.setItem(newFurniture);
        currentSim.setLocation(newFurniture.getPoint());

        System.out.println("\nObjek saat ini: " + currentSim.getItem().getName());
        System.out.printf("Masukkan 'Ya' untuk menggunakan objek\nMasukkan input lain untuk keluar dari menu ini\n> ");

        String answer = input.nextLine().trim();

        if (answer.toLowerCase().equals("ya")){
            System.out.println("");
            currentSim.getAction().useItem();
        }
    }

    public void viewInventory(){ // 9
        currentSim.getInventory().displayInventory();
    }

    public void editRoom(){ // 10
        if (!currentSim.getHouse().getOwner().getName().equals(currentSim.getName())){
            System.out.println(currentSim.getName() + " sedang tidak berada di rumahnya");
            return;
        }
        currentSim.getRoom().editRoom(currentSim);
    }

    public void upgradeHouse(){ // 11
        if (currentSim.getHouse().isUpgrading()){
            System.out.println("\nRumah " + currentSim.getName() + " sedang dibangun ruangan baru");
            return;
        }

        if (!currentSim.getHouse().getOwner().getName().equals(currentSim.getName())){
            System.out.println("\n" + currentSim.getName() + " sedang tidak berada di rumahnya");
            return;
        }

        if (currentSim.getStatus().getMoney() < 1500){
            System.out.println("\n" + currentSim.getName() + " tidak mempunyai uang yang cukup untuk membangun ruangan baru di rumahnya");
            return;
        }
        
        if (currentSim.getHouse().getRoomList().size() == 1){
            currentSim.getHouse().addRoom(currentSim.getHouse().getPrimaryRoom());
        } else{
            currentSim.getHouse().displayRoom();
            boolean valid = false;  

            while (!valid) {
                System.out.print("\nMasukkan nama ruangan yang dijadikan acuan: ");
                String roomName = input.nextLine().trim();
                    
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
    
    public void moveHouse(){ // 12
        if (!currentSim.getAction().isIdle()){
            System.out.println("\nSim sedang sibuk");
            return;
        }
        if (currentWorld.getListHouse().size() == 1){
            System.out.println("\nHanya ada 1 sim di world ini");
            return;
        }

        System.out.println("\nDaftar sim lain di world ini: ");
            for (Sim otherSim : currentSim.getWorld().getListSim()){
                if (!otherSim.getName().equals(currentSim.getName())){
                    System.out.println("> " + otherSim.getName());
                }
            }
        
        boolean valid = false;
        String visiteeString;
        while(!valid){
            System.out.print("\nMasukkan nama rumah sim yang ingin dikunjungi: ");
            visiteeString = input.nextLine().trim();
            if (visiteeString.isEmpty()) {
                System.out.println("\nMasukkan nama sim yang valid");
                continue;
            }
            else if ((visiteeString).toLowerCase().equals(currentSim.getHouse().getOwner().getName().toLowerCase())){
                System.out.println("\n" + currentSim.getName() + " sedang berada di Rumah "+ visiteeString);
                continue;
            }
            for (House house : currentWorld.getListHouse()){
                if ((visiteeString).toLowerCase().equals(house.getOwner().getName().toLowerCase())){
                    currentSim.moveHouse(house);
                    currentSim.setLocation(new Point(0,0));
                    currentSim.setItem(null);
                    valid = true;
                    break;
                }
            }

            if (!valid){
                System.out.println("\nTidak ada sim dengan nama tersebut");
            }
        }
    }

    public void displayMenu(){
        System.out.println("\n ▄▄       ▄▄ ▄▄▄▄▄▄▄▄▄▄▄ ▄▄        ▄ ▄         ▄ ");
        System.out.println("▐░░▌     ▐░░▐░░░░░░░░░░░▐░░▌      ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌░▌   ▐░▐░▐░█▀▀▀▀▀▀▀▀▀▐░▌░▌     ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌▐░▌ ▐░▌▐░▐░▌         ▐░▌▐░▌    ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌ ▐░▐░▌ ▐░▐░█▄▄▄▄▄▄▄▄▄▐░▌ ▐░▌   ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌  ▐░▌  ▐░▐░░░░░░░░░░░▐░▌  ▐░▌  ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌   ▀   ▐░▐░█▀▀▀▀▀▀▀▀▀▐░▌   ▐░▌ ▐░▐░▌       ▐░▌");
        System.out.println("▐░▌       ▐░▐░▌         ▐░▌    ▐░▌▐░▐░▌       ▐░▌");
        System.out.println("▐░▌       ▐░▐░█▄▄▄▄▄▄▄▄▄▐░▌     ▐░▐░▐░█▄▄▄▄▄▄▄█░▌");
        System.out.println("▐░▌       ▐░▐░░░░░░░░░░░▐░▌      ▐░░▐░░░░░░░░░░░▌");
        System.out.println(" ▀         ▀ ▀▀▀▀▀▀▀▀▀▀▀ ▀        ▀▀ ▀▀▀▀▀▀▀▀▀▀▀ ");
        System.out.println("\n1. Add Sim\n2. Change Sim\n3. View Sim Info\n4. Actions\n5. View Current Location\n6. Move Room\n7. Object List\n8. Go To Object\n9. View Inventory\n10. Edit Room\n11. Upgrade House\n12. Move House\n13. Help\n14. Exit");
    }

    public static void main(String[] args) throws Exception {
            SimPlicity game = SimPlicity.getInstance();                                                              
            System.out.println(" \n  ▄▄▄▄▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄▄▄▄▄ ▄▄       ▄▄                 ▄▄▄▄▄▄▄▄▄▄▄ ▄           ▄▄▄▄▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄▄▄▄▄ ▄         ▄ ");
            System.out.println(" ▐░░░░░░░░░░░▐░░░░░░░░░░░▐░░▌     ▐░░▌               ▐░░░░░░░░░░░▐░▌         ▐░░░░░░░░░░░▐░░░░░░░░░░░▐░░░░░░░░░░░▐░░░░░░░░░░░▐░▌       ▐░▌");
            System.out.println(" ▐░█▀▀▀▀▀▀▀▀▀ ▀▀▀▀█░█▀▀▀▀▐░▌░▌   ▐░▐░▌               ▐░█▀▀▀▀▀▀▀█░▐░▌          ▀▀▀▀█░█▀▀▀▀▐░█▀▀▀▀▀▀▀▀▀ ▀▀▀▀█░█▀▀▀▀ ▀▀▀▀█░█▀▀▀▀▐░▌       ▐░▌");
            System.out.println(" ▐░▌              ▐░▌    ▐░▌▐░▌ ▐░▌▐░▌               ▐░▌       ▐░▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌    ▐░▌       ▐░▌");
            System.out.println(" ▐░▌              ▐░▌    ▐░▌▐░▌ ▐░▌▐░▌               ▐░▌       ▐░▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌    ▐░▌       ▐░▌");
            System.out.println(" ▐░▌              ▐░▌    ▐░▌▐░▌ ▐░▌▐░▌               ▐░▌       ▐░▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌    ▐░▌       ▐░▌");
            System.out.println(" ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌    ▐░▌ ▐░▐░▌ ▐░▌  ▄▄▄▄▄▄▄▄▄▄▄  ▐░█▄▄▄▄▄▄▄█░▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌    ▐░█▄▄▄▄▄▄▄█░▌");
            System.out.println(" ▐░░░░░░░░░░░▌    ▐░▌    ▐░▌  ▐░▌  ▐░▌  ░░░░░░░░░░░  ▐░░░░░░░░░░░▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌    ▐░░░░░░░░░░░▌");
            System.out.println("  ▀▀▀▀▀▀▀▀▀█░▌    ▐░▌    ▐░▌   ▀   ▐░▌  ▀▀▀▀▀▀▀▀▀▀▀  ▐░█▀▀▀▀▀▀▀▀▀▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌     ▀▀▀▀█░█▀▀▀▀ ");
            System.out.println("           ▐░▌    ▐░▌    ▐░▌       ▐░▌               ▐░▌         ▐░▌              ▐░▌    ▐░▌              ▐░▌         ▐░▌         ▐░▌     ");
            System.out.println("  ▄▄▄▄▄▄▄▄▄█░▌▄▄▄▄█░█▄▄▄▄▐░▌       ▐░▌               ▐░▌         ▐░█▄▄▄▄▄▄▄▄▄ ▄▄▄▄█░█▄▄▄▄▐░█▄▄▄▄▄▄▄▄▄ ▄▄▄▄█░█▄▄▄▄     ▐░▌         ▐░▌    ");
            System.out.println(" ▐░░░░░░░░░░░▐░░░░░░░░░░░▐░▌       ▐░▌               ▐░▌         ▐░░░░░░░░░░░▐░░░░░░░░░░░▐░░░░░░░░░░░▐░░░░░░░░░░░▌    ▐░▌         ▐░▌     ");
            System.out.println("  ▀▀▀▀▀▀▀▀▀▀▀ ▀▀▀▀▀▀▀▀▀▀▀ ▀         ▀                 ▀           ▀▀▀▀▀▀▀▀▀▀▀ ▀▀▀▀▀▀▀▀▀▀▀ ▀▀▀▀▀▀▀▀▀▀▀ ▀▀▀▀▀▀▀▀▀▀▀      ▀           ▀   ");
            System.out.print("\nSelamat datang di Sim-Plicity! :3\n\nMasukkan 'START' untuk memulai game Sim-Plicity\nMasukkan input lain untuk keluar dari program\n\n> ");

            String strInput = input.nextLine().trim();

            if (!strInput.toLowerCase().equals("start")){
                game.exit();
            } else{
                game.startGame();
            }


            while (true){
                game.checkGameOver();
                game.displayMenu();
                System.out.println("\nMasukkan angka dari 1-14: ");
                System.out.print("> ");
                if (input.hasNext()){
                    int intInput = input.nextInt();
                    input.nextLine();
                    switch (intInput){
                        case 1:
                            game.addSim();
                            break;
                        case 2:
                            game.changeSim();
                            break;
                        case 3:
                            game.viewSimInfo();
                            break;
                        case 4:
                            game.action();
                            break;
                        case 5:
                            game.viewCurrentLocation();
                            break;
                        case 6:
                            game.moveRoom();
                            break;
                        case 7:
                            game.listObject();
                            break;
                        case 8:
                            game.goToObject();
                            break;
                        case 9:
                            game.viewInventory();
                            break;
                        case 10:
                            game.editRoom();
                            break;
                        case 11:
                            game.upgradeHouse();
                            break;
                        case 12:
                            game.moveHouse();
                            break;
                        case 13:
                            game.help();
                            break;
                        case 14:
                            game.exit();
                            break;
                        default:
                            System.out.println("\n\nMasukkan angka yang valid, yaitu 1-14\n");
                    }
                }
            }
    }
    public void changeDayUpdate(){
        hasAddSim = false;
    }

    public boolean isUsed(){
        return true;
    }
}
