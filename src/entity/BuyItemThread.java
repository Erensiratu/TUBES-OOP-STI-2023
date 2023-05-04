package entity;

public class BuyItemThread extends PassiveThread {

    private Sim sim;
    private Item finalItem;
    public BuyItemThread(long duration, Sim sim, Item finalItem){
        setDuration(duration);
        this.finalItem = finalItem;
        this.sim = sim;
        this.setName("Pembelian "+finalItem.getName()+" oleh "+sim.getName());
    }
    public void run(){
        System.out.println("Barang sedang diantar ke " + sim.getName());

        while(getDuration() > 0){

        }
        sim.getInventory().addItem(finalItem);
        System.out.println(sim.getName() + " telah menerima pesanannya");
    }
    public void changeSecUpdate(){
        durationDecrement();
    }

}