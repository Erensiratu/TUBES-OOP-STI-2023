package entity;
import java.util.*;

import entity.sim.Sim;
import entity.Timer;
import entity.item.Useable;
import entity.House;
import entity.Room;

public class SimPlicity {
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
