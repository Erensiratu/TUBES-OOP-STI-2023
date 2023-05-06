import java.io.Serializable;

import javax.print.attribute.HashPrintServiceAttributeSet;

public class StatusSaver implements Serializable {
    private double money;
    private double hunger;
    private double health;
    private double mood;
    private boolean alive;

    public double getMoney() {
        return money;
    }
    public double getHunger() {
        return hunger;
    }
    public double getHealth() {
        return health;
    }
    public double getMood() {
        return mood;
    }
    public boolean isAlive() {
        return alive;
    }

    public StatusSaver(Status s){
        money = s.getMoney();
        hunger = s.getHunger();
        health = s.getHealth();
        mood = s.getMood();
        alive = s.isAlive();
    }
}