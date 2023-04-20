package entity.sim;
import entity.item.Item;
import entity.item.Useable;

public class Action {
    private boolean idle;
    private Sim sim;

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

    public void startActivity() {
        System.out.println("Sim is starting an activity.");
    }

    public void exercise(int duration) {
        System.out.println("Sim is exercising for " + duration + " minutes.");
    }

    public void giftItem(Item gift, Sim receiver) {
        System.out.println("Sim is gifting " + gift.getName() + " to " + receiver.getName() + ".");
        receiver.getInventory().addItem(gift);
    }

    public void transferMoney(int amount, Sim receiver) {
        System.out.println("Sim is transferring $" + amount + " to " + receiver.getName() + ".");
        receiver.getStatus().addMoney(amount);
    }

    public void dayDream(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Daydreaming for " + duration + " hours...");

        // Simulate daydreaming for the given duration
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Done daydreaming!");
    }

    public void converse(Sim otherSim) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Conversing with " + otherSim + "...");

        // Simulate a conversation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Done conversing!");
    }

    public void work(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Working for " + duration + " hours...");

        // Simulate working for the given duration
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        idle = true;
        System.out.println("Done working!");
    }

    public void useItem(int duration) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        Useable item = (Useable) sim.getItem();

        idle = false;
        System.out.println("Using item " + item + "...");

        // Use the item
        item.use(sim);

        idle = true;
        System.out.println("Done using item!");
    }

    public void buyItem(Item item) {
        sim.getInventory().addItem(item);
        System.out.println("Sim has bought " + item.getName() + ".");
    }
}
