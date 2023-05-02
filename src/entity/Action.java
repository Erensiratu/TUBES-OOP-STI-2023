package entity;
import java.util.Scanner;


public class Action {
    private boolean idle;
    private Sim sim;
    Scanner scanner = new Scanner(System.in);

    public Action(Sim sim){
        idle = true;
        this.sim = sim;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public void exercise() {
        int input = -1;

        while ((input % 20 != 0) || (input <= 0)){
            System.out.println("\nMasukkan durasi kerja dalam detik\n Durasi olahraga: ");
            input = scanner.nextInt();

            if ((input % 120 != 0) || (input <= 0)){
                System.out.println("Durasi kerja harus dalam kelipatan 20 dan lebih dari 0");
            }
        }

        System.out.println(sim.getName()+" akan melakukan olahraga selama "+ input + " detik");

        final int finalInput = input;

        Thread excerciseThread = new Thread(() -> {
    
            System.out.println(sim.getName() + " sedang berolahraga");
                
            try {
                Thread.sleep(finalInput * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sim.getStatus().addHealth(finalInput/20*5);
            sim.getStatus().addMood(finalInput/20*10);
            sim.getStatus().decreaseHunger(finalInput/20*5);

            System.out.println(sim.getName() + " telah selesai berolahraga");
        });

        // Memulai thread
        excerciseThread.start();
        try {
            excerciseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startActivity() {
        System.out.println("\nDaftar Aktivitas:\n1. Kerja\n2. Olahraga\n3. Mengobrol\n4. Transfer Uang\n5. Menghadiahkan Barang\n6. Menghayal\n7. Menggunakan Objek\n8. Membeli Barang\n9. Ganti Pekerjaan\n10. Batal\n");

        int input = 0;
        while (input < 1 || input > 9){
            System.out.printf("\nMasukkan angka dari 1-9: ");
            input = scanner.nextInt();

            if (input < 1 || input > 9){
                System.out.println("\n\nMasukan tidak valid, silakan ulangi");
            }
        }

        switch(input){
            case 1:
                work();
                break;
            case 2:
                exercise();
                break;
            case 3:
                converse();
                break;
            case 4:
                transferMoney();
                break;
            case 5:
                giftItem();
                break;
            case 6:
                dayDream();
                break;
            case 7:
                useItem();
                break;
            case 8: 
                buyItem();
                break;
            case 9:
                changeProfession();
                break;
            case 10:
                return;
        }
    }

    public void changeProfession(){
        sim.getOcupation().changeProfession();
    }
    

    public void giftItem() {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        if (sim.getInventory().getList().size() == 0){
            System.out.println(sim.getName() + " tidak mempunyai barang di inventory-nya");
            return;
        }

        if (sim.getWorld().getListSim().size() == 1){
            System.out.println("Tidak ada sim lain di world ini");
            return;
        }

        Sim receiver = null;
        Item gift = null;
        String receiverName = "";
        String itemName = "";
        int quantity = 0;
        boolean found = false;
        
        System.out.println("Daftar sim lain di world ini: ");
        for (Sim otherSim : sim.getWorld().getListSim()){
            if (!otherSim.getName().equals(sim.getName())){
                System.out.println("> " + otherSim.getName());
            }
        }
        
        while (!found){
            System.out.print("Masukkan nama penerima: ");
            receiverName = scanner.nextLine().trim();

            if (receiverName.toLowerCase().equals(sim.getName().toLowerCase())){
                System.out.println("\n\nTidak dapat memberikan hadiah ke diri sendiri");
                continue;
            }

            for (Sim otherSim : sim.getWorld().getListSim()){
                if (otherSim.getName().toLowerCase().equals(receiverName.toLowerCase())){
                    receiver = otherSim;
                    found = true;
                    break;
                }
            }

            if (!found){
                System.out.println("\n\nTidak ada sim dengan nama " + receiverName);
            }

            if (receiverName.isEmpty()){
                System.out.println("\n\nMasukkan nama sim yang valid" );
            }
        }

        if (!(sim.getHouse().getName().equals(receiver.getHouse().getName())) && (sim.getHouse().getName().equals(receiver.getHouse().getName()))){
            System.out.println(sim.getName() + " sedang tidak berada di tempat yang sama dengan " + receiver.getName());  
            return;
        }

        sim.getInventory().displayInventory();

        found = false;
        while (!found){
            System.out.print("\n\n\nMasukkan nama item yang ingin dihadiahkan: ");
            itemName = scanner.nextLine().trim();

            for (Item item : sim.getInventory().getList()){
                if (item.getName().toLowerCase().equals(itemName.toLowerCase())){
                    if (item.getQuantity() == 1){
                        quantity = 1;
                    }
                    while (quantity <= 0 || quantity > item.getQuantity()){
                        System.out.print("\n\n\nMasukkan jumlah" + item.getName() + " yang ingin dihadiahkan: ");
                        quantity = scanner.nextInt();

                        if (quantity <= 0){
                            System.out.println("\n\n Jumlah harus lebih dari 0");
                            continue;
                        }

                        if (quantity > item.getQuantity()){
                            System.out.println("\n\n" + item.getName() + " hanya tersedia " + item.getQuantity() + " buah");
                            continue;
                        }
                    }
                    gift = item;
                    found = true;
                    break;
                }
            }

            if (!found){
                System.out.println("\n\nTidak ada itam dengan nama " + itemName);
            }

            if (receiverName.isEmpty()){
                System.out.println("\n\nMasukkan nama item yang valid" );
            }
        }
        
        if (gift instanceof Furniture){
            receiver.getInventory().addItem((Item) FurnitureFactory.createFurniture(itemName, quantity));
        } else if (gift instanceof Ingredient){
            receiver.getInventory().addItem((Item) IngredientFactory.createIngredient(itemName, quantity));
        } else if (gift instanceof Cuisine){
            receiver.getInventory().addItem((Item) CuisineFactory.createCuisine(itemName, quantity));
        }

        sim.getInventory().removeItem(gift, quantity);

        System.out.println(sim.getName() + " menghadiahkan " + gift.getName() + " ke " + receiver.getName() + " sebanyak " + quantity + " buah");     
    }

    public void transferMoney() {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        if (sim.getStatus().getMoney() == 0){
            System.out.println(sim.getName() + " tidak mempunyai uang");
            return;
        }

        if (sim.getWorld().getListSim().size() == 1){
            System.out.println("Tidak ada sim lain di world ini");
            return;
        }

        Sim receiver = null;
        String receiverName = "";
        int quantity = 0;
        boolean found = false;
        
        System.out.println("Daftar sim lain di world ini: ");
        for (Sim otherSim : sim.getWorld().getListSim()){
            if (!otherSim.getName().equals(sim.getName())){
                System.out.println("> " + otherSim.getName());
            }
        }
        
        while (!found){
            System.out.print("Masukkan nama penerima: ");
            receiverName = scanner.nextLine().trim();

            if (receiverName.toLowerCase().equals(sim.getName().toLowerCase())){
                System.out.println("\n\nTidak dapat memberikan uang ke diri sendiri");
                continue;
            }

            for (Sim otherSim : sim.getWorld().getListSim()){
                if (otherSim.getName().toLowerCase().equals(receiverName.toLowerCase())){
                    receiver = otherSim;
                    found = true;
                    break;
                }
            }

            if (!found){
                System.out.println("\n\nTidak ada sim dengan nama " + receiverName);
            }

            if (receiverName.isEmpty()){
                System.out.println("\n\nMasukkan nama sim yang valid" );
            }
        }

        System.out.println("\n\nJumlah uang milik " + sim.getName() + " : " + sim.getStatus().getMoney());

        while (quantity <= 0 || quantity > sim.getStatus().getMoney()){
            System.out.print("\n\n\nMasukkan jumlah uang yang ingin dikirim: ");
            quantity = scanner.nextInt();

            if (quantity <= 0){
                System.out.println("\n\nMasukkan jumlah yang valid");
                continue;
            }

            if (quantity > sim.getStatus().getMoney()){
                System.out.println("\n\n" + sim.getName() + " tidak mempunyai uang sebanyak " + quantity);
                continue;
            }
        }
        
        receiver.getStatus().addMoney(quantity);
        sim.getStatus().decreaseMoney(quantity);

        System.out.println(sim.getName() + " mengirim uang sebanyak " + quantity + " ke " + receiver.getName());     
    }

    public void dayDream() {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }
        
        System.out.print("Masukkan durasi berkhayal: ");
        int durationInput = scanner.nextInt();

        scanner.nextLine();
        while (durationInput <= 0){
            System.out.println("\n\nInput durasi tidak valid.");
            System.out.print("\nMasukkan durasi berkhayal: ");
            durationInput = scanner.nextInt();
            scanner.nextLine();
        } 
        int duration = durationInput;
        System.out.println("\n\nBerkhayal selama " + duration + " detik...");
        Thread dreamThread = new Thread( new Runnable() {
            public void run() {
                idle = false;

                try {
                    Thread.sleep(duration*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sim.getStatus().addMood(duration/60 + 1);
                System.out.println("Selesai berkhayal!");
                idle = true;
            }
        });
        dreamThread.start();
        try {
            dreamThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void converse() {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }
        if (sim.getWorld().getListSim().size() == 1){
            System.out.println("Tidak ada sim lain di world ini");
            return;
        }
        Sim otherSim2 = null ;
        String otherSimName = "";
        boolean found = false;
        while (!found){
            System.out.print("Masukkan nama sim: ");
            otherSimName = scanner.nextLine().trim();

            if (otherSimName.toLowerCase().equals(sim.getName().toLowerCase())){
                System.out.println("\n\nTidak dapat berbicara ke diri sendiri");
                continue;
            }

            for (Sim i : sim.getWorld().getListSim()){
                if (i.getName().toLowerCase().equals(otherSimName.toLowerCase())){
                    otherSim2 = i;
                    found = true;
                    break;
                }
            }

            if (!found){
                System.out.println("\n\nTidak ada sim dengan nama " + otherSimName);
            }

            if (otherSimName.isEmpty()){
                System.out.println("\n\nMasukkan nama sim yang valid" );
            }
        }
        if (!((sim.getHouse().getName().equals(otherSim2.getHouse().getName())) && (sim.getHouse().getName().equals(otherSim2.getHouse().getName())))){
            System.out.println(sim.getName() + " sedang tidak berada di tempat yang sama dengan " + otherSim2.getName());  
            return;
        }
        if (!otherSim2.getAction().isIdle()) {
            System.out.println("Maaf, "+ otherSim2.getName() +" sedang sibuk.");
            return;
        }

        // Simulate a conversation
        Sim otherSim = otherSim2;
        System.out.println( sim.getName() +" mengobrol dengan " + otherSim.getName() + "...");
        
        Thread converseThread = new Thread( new Runnable() {
            public void run() {
                idle = false;
                otherSim.getAction().setIdle(false);
                
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sim.getStatus().addMood(5);
                otherSim.getStatus().addMood(5);
                System.out.println("Selesai mengobrol!");
                idle = true;
                otherSim.getAction().setIdle(true);
            }
        });
        converseThread.start();
        try {
            converseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void work() {
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }
        if (sim.getHadWorkedToday()){
            System.out.println("Maaf, Sim telah bekerja hari ini");
            return;
        }
        sim.setHadWorkedToday(true);
        sim.getOcupation().doWork();
    }

    public void useItem() { 
        if (!idle) {
            System.out.println("Maaf, Sim sedang sibuk.");
            return;
        }

        if (!((Furniture) sim.getItem()).getVacancy()){
            System.out.println("Maaf, benda sedang digunakan oleh sim lain.");
            return;
        }

        Useable item = (Useable) sim.getItem();

        idle = false;
        System.out.println(sim.getName() + " menggunakan " + ((Item) item).getName() + "...");

        // Use the item
        item.use(sim);

        idle = true;
        System.out.println(sim.getName() + " selesai menggunakan " + ((Item) item).getName() + "...");
    }

    public void buyItem(){
        Purchaseable item = null;
        
        int quantity = -1;
        String itemName = "";
        System.out.println("Silakan pilih kategori belanja:\n1 untuk Furnitur\n2 untuk Bahan Makanan\n3 untuk Batal\n");
        
        int input = -1;
        while ((input < 1) || (input > 3)){
            System.out.printf("\nMasukkan nomor: ");
            input = scanner.nextInt();
            System.out.println();

            if((input < 1) || (input > 3)){
                System.out.println("Tolong masukkan input yang valid");
            }
        }
        while (item == null){
            switch (input){
                case 1:
                    System.out.println("Daftar Furnitur:\n1. Kasur Single\n2. Kasur Queen\n3. Kasur King\n4. Kompor Gas\n5. Kompor Listrik\n6. Kanvas\n7. Jam\n8. Shower\n9. Meja dan Kursi\n10. Toilet\n");
                    while (itemName.isEmpty()){
                        System.out.printf("Masukkan nama furnitur: ");
                        itemName = scanner.nextLine();
                    }
                    item = (Purchaseable) FurnitureFactory.createFurniture(itemName, 0);
                    break;
                case 2:
                    System.out.println("Daftar Bahan Makanan:\n1. Ayam\n2. Bayam\n3. Kacang\n4. Kentang\n5. Nasi\n6. Sapi\n7. Susu\n8. Wortel\n");
                    while (itemName.isEmpty()){
                        System.out.printf("Masukkan nama bahan makanan: ");
                        itemName = scanner.nextLine();
                    }
                    item = (Purchaseable) IngredientFactory.createIngredient(itemName, 0);
                    break;
                case 3:
                    return;
                default:
                    throw new IllegalArgumentException("Invalid option: " + input);
            }

            if (item == null){
                System.out.println("\nNama item tidak valid, mohon ulangi masukkan\n\n");
            }
        }
        
        while (quantity <= 0){
            System.out.printf("Masukkan jumlah barang: ");
            quantity = scanner.nextInt();
            System.out.println();
        }
    
        final Item finalItem = (Item) item;
        finalItem.add(quantity);
        
        
        if (sim.getStatus().getMoney() >= item.getPrice() * quantity){
            sim.getStatus().decreaseMoney(item.getPrice() * quantity);
            final int deliveryTime = item.getDelliveryTime();
            BuyItemThread buyThread = new BuyItemThread(deliveryTime, sim, finalItem);
            // Memulai thread
            buyThread.start();
            Sim.getCurrentWorld().getClock().addSecEventListener(buyThread);
        } else{
            System.out.println("Uang milik " + sim.getName() + " tidak cukup untuk membeli barang ini");
        }


    }
    public class BuyItemThread extends Thread implements TickListener {
        private long duration ;
        private Sim sim;
        private Item finalItem;
        public BuyItemThread(long duration, Sim sim, Item finalItem){
            this.duration = duration;
            this.finalItem = finalItem;
            this.sim = sim;

        }
        public void run(){
            System.out.println("Barang sedang diantar ke " + sim.getName());

            while(duration > 0){

            }
            sim.getInventory().addItem(finalItem);
            System.out.println(sim.getName() + " telah menerima pesanannya");
        }
        public void changeSecUpdate(){
            durationDecrement();
        }
        public synchronized void durationDecrement(){
            duration = duration -  1000;
        }

    }
    
}