import java.io.Serializable;


public class PassiveThreadSaver implements Serializable{
    private long duration ;
    private String args;
    private int type;

    public PassiveThreadSaver(PassiveThread p){
        duration = p.getDuration();
        args = p.getArg();
        type = p.getType();
    }

    public long getDuration(){
        return duration;
    }

    public int getType(){
        return type;
    }

    public String getArg(){
        return args;
    }
    
}