

public class Police extends Profession {
    public Police() {
        super(35);
    }

    public static Police getInstance(){
        return new Police();
    }

    @Override
    public String getName() {
        return "Polisi";
    }
}
