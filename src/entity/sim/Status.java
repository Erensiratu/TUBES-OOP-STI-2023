package entity.sim;

public class Status {
    private double money;
    private double hunger;
    private double health;
    private double mood;
    
    public Status() {
        money = 100;
        hunger = 80;
        health = 80;
        mood = 80;
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
    

    public void addMoney(double value){
        if (money + value > 100){
            money = 100;
        } else {
            money += value;
        }
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
}
