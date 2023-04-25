package entity.sim.profession;

public class Doctor extends Profession {
    public Doctor() {
        super(50);
    }

    public static Doctor getInstance(){
        return new Doctor();
    }

    @Override
    public String getName() {
        return "Dokter";
    }
}
