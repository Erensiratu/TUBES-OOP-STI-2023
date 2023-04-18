package entity.sim.profession;

public class Programmer extends Profession {
    public Programmer() {
        super(45);
    }

    @Override
    public String getName() {
        return "Programmer";
    }
}