import java.io.Serializable;


public class OccupationSaver implements Serializable {
    String professionName;
    int timesWorked;
    int daySinceChangeJob;
    int shiftWorked;

    public OccupationSaver(Occupation occupation){
        professionName = occupation.getProfession().getName();
        timesWorked = occupation.getTimesWorked();
        daySinceChangeJob = occupation.getShiftWorked();
    }

    public String getProfessionName(){
        return professionName;
    }

    public int getTimesWorked(){
        return timesWorked;
    }
    public int getDaySinceChangeJob(){
        return daySinceChangeJob;
    }
    public int getShiftWorked(){
        return shiftWorked;
    }
}