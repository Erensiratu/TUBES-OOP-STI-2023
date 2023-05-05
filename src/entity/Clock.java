package entity;



public class Clock extends Furniture{
    public Clock(int quantity){
        super(quantity, 1, 1, 10);
    }

    public static Clock getInstance(int quantity){
        return new Clock(quantity);
    }

    public void use(Sim sim){
        System.out.println("Waktu dalam hari ini tersisa : " +  Sim.getCurrentWorld().getClock().getRemainingTime()/1000 + " detik");
        for(PassiveThread i : Sim.getCurrentWorld().getClock().getPassiveThread()){
            System.out.println(i.getName() + " akan tersisa "+ i.getDuration()/1000 + " detik");
        }
    }

    @Override
    public String getDescription(){
        return "Buat melihat waktu";
    }

    @Override
    public String getName(){
        return "Jam";
    }
}
