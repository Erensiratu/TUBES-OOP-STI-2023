package entity.sim;

public class Status {
    private int money;
    private int hunger;
    private int health;
    private int mood;
    
    public Status() {
        money = 100;
        hunger = 80;
        health = 80;
        mood = 80;
    }

    public int getMoney() {
        return money;
    }
    public int getHunger() {
        return hunger;
    }
    public int getHealth() {
        return health;
    }
    public int getMood() {
        return mood;
    }
    

    public void addMoney(int value){
        if (money + value > 100){
            money = 100;
        } else {
            money += value;
        }
    }
    public void addHunger(int value){
        if (hunger + value > 100){
            hunger = 100;
        } else {
            hunger += value;
        }
    }
    public void addHealth(int value){
        if (health + value > 100){
            health = 100;
        } else {
            health += value;
        }
    }
    public void addMood(int value){
        if (mood + value > 100){
            mood = 100;
        } else {
            mood += value;
        }
    }
}
