package entity.sim.profession;

public class Doctor extends Profession {
    public Doctor() {
        super(50);
    }

    @Override
    public String getName() {
        return "Dokter";
    }
}
