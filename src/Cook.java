

public class Cook extends Profession {
    public Cook() {
        super(30);
    }

    public static Cook getInstance(){
        return new Cook();
    }

    @Override
    public String getName() {
        return "Koki";
    }
}