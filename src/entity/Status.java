package entity;

import java.util.ArrayList;



public class Status {
    private double money;
    private double hunger;
    private double health;
    private double mood;
    private boolean alive;
    private ArrayList<StatusModifier> modifiers;
    public Status() {
        money = 100;
        hunger = 80;
        health = 80;
        mood = 80;
        modifiers = new ArrayList<StatusModifier>();
        alive = true;
    }

    public double getMoney() {
        double outputMoney = money;
        for (StatusModifier i : modifiers){
            outputMoney += i.getMoney();
        }

        return outputMoney;
    }
    public double getHunger() {
        double outputHunger = hunger;
        for (StatusModifier i : modifiers){
            outputHunger += i.getHunger();
        }
        return outputHunger;
    }
    public double getHealth() {
        double outputHealth = health;
        for (StatusModifier i : modifiers){
            outputHealth += i.getHealth();
        }
        return outputHealth;
    }
    public double getMood() {
        double outputMood = mood;
        for (StatusModifier i : modifiers){
            outputMood += i.getMood();
        }
        return outputMood;
    }
    public boolean isAlive() {
        return alive;
    }
    
    public void kill(){
        alive = false;
    }

    public synchronized void addMoney(double value){
        money += value;
    }
    public synchronized void addHunger(double value){
        if (hunger + value > 100){
            hunger = 100;
        } else {
            hunger += value;
        }
    }
    public synchronized void addHealth(double value){
        if (health + value > 100){
            health = 100;
        } else {
            health += value;
        }
    }
    public synchronized void addMood(double value){
        if (mood + value > 100){
            mood = 100;
        } else {
            mood += value;
        }
    }

    public synchronized void decreaseMoney(double value){
        if (money - value < 0){
            money = 0;
        } else {
            money -= value;
        }
    }
    public synchronized void decreaseHunger(double value){
        if (hunger - value < 0){
            hunger = 0;
            kill();
        } else {
            hunger -= value;
        }
    }
    public synchronized void decreaseHealth(double value){
        if (health - value < 0){
            health = 0;
            kill();
        } else {
            health -= value;
        }
    }
    public synchronized void decreaseMood(double value){
        if (mood - value < 0){
            mood = 0;
            kill();
        } else {
            mood -= value;
        }
    }
    public void removeModifier(String name){
        int idx = 0;
        while (idx < modifiers.size()){
            if (modifiers.get(idx).getName().equals(name)){
                modifiers.remove(idx);
            } else {
                idx++;
            }
        }
    }
    public void addModifier(StatusModifier mod){
        modifiers.add(mod);
    }


    public void displayStatus() {
        System.out.println("\nStatus Sim");
        System.out.println("Uang        : " + money);
        System.out.println("Kekenyangan : " + hunger);
        System.out.println("Kesehatan   : " + health);
        System.out.println("Mood        : " + mood);
    }
}
