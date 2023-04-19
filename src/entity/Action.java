package entity;
import java.util.List;
import entity.item.Item;
import entity.sim.Sim;
import entity.item.Useable;

public abstract class Action {
    private boolean idle;


    public boolean isIdle() {
        return idle;
    }

    public void settle(boolean idle) {
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
        receiver.getInventory().add(gift);
    }

    public void transferMoney(int amount, Sim receiver) {
        System.out.println("Sim is transferring $" + amount + " to " + receiver.getName() + ".");
        receiver.money -= amount;
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

    public void useItem(Item item) {
        if (!idle) {
            System.out.println("Sorry, I'm busy right now.");
            return;
        }

        idle = false;
        System.out.println("Using item " + item + "...");

        // Use the item
        Sim sim;
        int duration;
        item.use(sim,duration);

        idle = true;
        System.out.println("Done using item!");
    }

    public void buyItem(List<Item> inventory, Item item) {
        inventory.add(item);
        System.out.println("Sim has bought " + item.getName() + ".");
    }
}
