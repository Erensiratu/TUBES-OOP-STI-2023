package entity;

public class Programmer extends Profession {
    public Programmer() {
        super(45);
    }

    public static Programmer getInstance(){
        return new Programmer();
    }

    @Override
    public String getName() {
        return "Programmer";
    }
}