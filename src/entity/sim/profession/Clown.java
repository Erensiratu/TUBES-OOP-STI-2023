package entity.sim.profession;

public class Clown extends Profession {
    public Clown() {
        super(15);
    }

    public static Clown getInstance(){
        return new Clown();
    }

    @Override
    public String getName() {
        return "Badut Sulap";
    }
}