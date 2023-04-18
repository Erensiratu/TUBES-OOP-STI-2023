package entity.sim.profession;

public class Cook extends Profession {
    public Cook() {
        super(30);
    }

    @Override
    public String getName() {
        return "Koki";
    }
}