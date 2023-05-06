

public abstract class Profession {
    private int salary;
    // kenapa nggak dibikin id?? :(
    public Profession(int salary){
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public abstract String getName();
}
