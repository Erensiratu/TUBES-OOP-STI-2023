package entity.sim;

public class StatusModifier{
    private double money;
    private double hunger;
    private double health;
    private double mood;
    private String name;

    public StatusModifier(String name, double mood, double health, double hunger, double money){
        this.name = name;
        this.health = health;
        this.hunger = hunger;
        this.money = money;
        this.mood = mood;
    }

    public StatusModifier clone(){
        return new StatusModifier(name, mood, health, hunger, money);
    }

    public double getMoney(){
        return money;
    }
    public double getHunger(){
        return hunger;
    }
    public double getHealth(){
        return health;
    }
    public double getMood(){
        return mood;
    }
    public String getName(){
        return name;
    }

}