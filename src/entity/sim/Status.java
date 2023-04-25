package entity.sim;

public class Status {
    private double money;
    private double hunger;
    private double health;
    private double mood;
    private boolean alive;
    
    public Status() {
        money = 100;
        hunger = 80;
        health = 80;
        mood = 80;
        alive = true;
    }

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
    
    public void kill(){
        alive = false;
    }

    public void addMoney(double value){
        money += value;
    }
    public void addHunger(double value){
        if (hunger + value > 100){
            hunger = 100;
        } else {
            hunger += value;
        }
    }
    public void addHealth(double value){
        if (health + value > 100){
            health = 100;
        } else {
            health += value;
        }
    }
    public void addMood(double value){
        if (mood + value > 100){
            mood = 100;
        } else {
            mood += value;
        }
    }

    public void decreaseMoney(double value){
        if (money - value < 0){
            money = 0;
        } else {
            money -= value;
        }
    }
    public void decreaseHunger(double value){
        if (hunger - value < 0){
            hunger = 0;
            kill();
        } else {
            hunger -= value;
        }
    }
    public void decreaseHealth(double value){
        if (health - value < 0){
            health = 0;
            kill();
        } else {
            health -= value;
        }
    }
    public void decreaseMood(double value){
        if (mood - value < 0){
            mood = 0;
            kill();
        } else {
            mood -= value;
        }
    }
}
